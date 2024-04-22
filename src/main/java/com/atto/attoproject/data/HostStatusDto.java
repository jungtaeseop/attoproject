package com.atto.attoproject.data;

import com.atto.attoproject.config.exception.error.CustomException;
import com.atto.attoproject.domain.Host;
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

    public HostStatusDto(Host host) {
        this.id = host.getId();
        this.ip = host.getIp();
        this.name = host.getName();
        this.alive = host.getStatus().getAlive();
        this.lastStatusCheckeDate = host.getStatus().getLastStatusCheckeDate();
    }

    public static HostStatusDto from(Host host) {
        return new HostStatusDto(host);
    }

}
