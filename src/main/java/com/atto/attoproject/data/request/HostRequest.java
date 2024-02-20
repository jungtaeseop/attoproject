package com.atto.attoproject.data.request;

import com.atto.attoproject.data.HostDto;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class HostRequest {
    @NotBlank(message = "네트워크 이름")
    private String name;
    @NotBlank(message = "ip")
    private String ip;

    public HostDto toDto() {
        return HostDto.builder()
                .name(this.name)
                .ip(this.ip)
                .build();
    }
}
