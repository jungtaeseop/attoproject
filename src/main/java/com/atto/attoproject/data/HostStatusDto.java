package com.atto.attoproject.data;

import com.atto.attoproject.config.exception.error.CustomException;
import com.atto.attoproject.domain.HostStatus;
import com.atto.attoproject.domain.enums.Alive;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;

import java.net.InetAddress;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class HostStatusDto {

    private Long id;
    private String ip;
    private String name;
    private Alive alive;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private LocalDateTime lastStatusCheckeDate;

    public HostStatusDto(HostStatus hostStatus) {
        this.id = hostStatus.getId();
        this.alive = hostStatus.getAlive();
        this.lastStatusCheckeDate = hostStatus.getLastStatusCheckeDate();
    }

    public static HostStatusDto of(HostStatus hostStatus) {
        return new HostStatusDto(hostStatus);
    }

    public void updateStatus() {
        try {
            InetAddress address = InetAddress.getByName(this.ip);
            this.alive = address.isReachable(100) ? Alive.Enabled : Alive.Disabled;
            this.lastStatusCheckeDate = LocalDateTime.now();
        } catch (Exception e) {
            //e.printStackTrace();
            throw CustomException.of("400","host check 중 오류", HttpStatus.BAD_REQUEST);
        }
    }
}
