package com.atto.attoproject.domain;

import com.atto.attoproject.config.basedomain.BaseEntity;
import com.atto.attoproject.data.HostDto;
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

    @OneToOne(mappedBy = "host", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private HostStatus status;

    public Host(HostDto hostDto) {
        this.name = hostDto.getName();
        this.ip = hostDto.getIp();
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

}
