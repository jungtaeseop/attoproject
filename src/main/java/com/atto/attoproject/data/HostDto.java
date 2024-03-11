package com.atto.attoproject.data;

import com.atto.attoproject.config.basedto.BaseDto;
import com.atto.attoproject.domain.Host;
import lombok.Builder;
import lombok.Getter;

@Getter
public class HostDto extends BaseDto {
    private Long id;
    private String name;
    private String ip;

    @Builder
    public HostDto(String name, String ip) {
        this.name = name;
        this.ip = ip;
    }

    public HostDto(Host host) {
        this.id = host.getId();
        this.name = host.getName();
        this.ip = host.getIp();
        this.createdDate = host.getCreatedDate();
        this.lastModifiedDate = host.getLastModifiedDate();
    }

    public static HostDto from(Host host) {
        return new HostDto(host);
    }

    public static HostDto of(String name, String ip) {
        return new HostDto(name, ip);
    }


}
