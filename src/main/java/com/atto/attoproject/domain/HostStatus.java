package com.atto.attoproject.domain;

import com.atto.attoproject.config.exception.error.CustomException;
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
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class HostStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Alive alive;

    private LocalDateTime lastStatusCheckeDate;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "host_id")
    private Host host;

    private HostStatus(Host host) {
        this.host = host;
        updateStatus();
    }

    public static HostStatus from(Host host) {
        return new HostStatus(host);
    }

    public void updateStatus() {
        try {
            InetAddress address = InetAddress.getByName(host.getIp());
            this.alive = address.isReachable(1) ? Alive.Enabled : Alive.Disabled;
            this.lastStatusCheckeDate = LocalDateTime.now();
        } catch (Exception e) {
            //e.printStackTrace();
            throw CustomException.of("400","host check 중 오류", HttpStatus.BAD_REQUEST);
        }
    }
}
