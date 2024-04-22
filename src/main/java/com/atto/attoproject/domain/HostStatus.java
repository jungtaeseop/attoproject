package com.atto.attoproject.domain;

import com.atto.attoproject.config.exception.error.CustomException;
import com.atto.attoproject.data.HostDto;
import com.atto.attoproject.data.HostStatusDto;
import com.atto.attoproject.domain.enums.Alive;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.net.InetAddress;
import java.time.LocalDateTime;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HostStatus {

    @Enumerated(EnumType.STRING)
    private Alive alive;
    private LocalDateTime lastStatusCheckeDate;

    private HostStatus(Host host) {
        updateStatus(host);
    }

    public static HostStatus from(Host host) {
        return new HostStatus(host);
    }

    public void updateStatus(Host host) {
        try {
            InetAddress address = InetAddress.getByName(host.getIp());
            this.alive = address.isReachable(100) ? Alive.Enabled : Alive.Disabled;
            this.lastStatusCheckeDate = LocalDateTime.now();
        } catch (Exception e) {
            //e.printStackTrace();
            throw CustomException.of("400","host check 중 오류", HttpStatus.BAD_REQUEST);
        }
    }
}
