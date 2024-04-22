package com.atto.attoproject.domain;

import com.atto.attoproject.config.basedomain.BaseEntity;
import com.atto.attoproject.data.HostDto;
import com.atto.attoproject.data.HostStatusDto;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

@Getter
@Entity
@Table(name = "hosts")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Host extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    @Column(unique = true)
    private String ip;

    @Embedded
    private HostStatus status;

    public Host(HostDto hostDto) {
        this.name = hostDto.getName();
        this.ip = hostDto.getIp();
        this.status = hostDto.getStatus();
    }

    public static Host of(HostDto hostDto) {
        return new Host(hostDto);
    }

    public void update(HostDto hostDto) {
        if (StringUtils.hasText(hostDto.getName())) {
            this.name = hostDto.getName();
        }
        if (StringUtils.hasText(hostDto.getIp())) {
            this.ip = hostDto.getIp();
        }
    }

    public void createHostStatus() {
        this.status = HostStatus.from(this);
    }

    public void updateHostStatus() {
        this.status = HostStatus.from(this);
    }
}
