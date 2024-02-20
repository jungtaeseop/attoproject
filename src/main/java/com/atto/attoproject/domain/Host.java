package com.atto.attoproject.domain;

import com.atto.attoproject.config.basedomain.BaseEntity;
import com.atto.attoproject.data.HostDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
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
