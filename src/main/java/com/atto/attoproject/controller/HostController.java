package com.atto.attoproject.controller;

import com.atto.attoproject.config.basedto.BaseResponse;
import com.atto.attoproject.data.HostStatusDto;
import com.atto.attoproject.data.request.HostRequest;
import com.atto.attoproject.data.HostDto;
import com.atto.attoproject.domain.Host;
import com.atto.attoproject.service.HostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/hosts")
public class HostController {
    private final HostService hostService;

    @PostMapping
    public HostDto registerHost(@Valid @RequestBody HostRequest hostRequest){
        Host host = hostService.registerHost(hostRequest.toDto());
        return HostDto.from(host);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/list")
    public List<HostDto> getAllHosts() {
        List<Host> hostList = hostService.getAllHosts();
        return hostList.stream().map(HostDto::from).collect(Collectors.toList());
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/{id}")
    public HostDto getHostById(@PathVariable("id") Long id) {
        return HostDto.from(hostService.findById(id));
    }

    @PutMapping("/{id}")
    public HostDto updateHost(@PathVariable("id") Long id, @Valid @RequestBody HostRequest hostRequest) {
        Host host = hostService.updateHost(id, hostRequest.toDto());
        return HostDto.from(host);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<?> deleteHost(@PathVariable("id") Long id) {
        hostService.delete(id);
        return BaseResponse.successMessage("삭제 성공입니다.");
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/check/{id}")
    public BaseResponse<HostStatusDto> checkHost(@PathVariable("id") Long id) {
        HostStatusDto hostStatusDto = hostService.checkHostStatus(id);
        return BaseResponse.success(hostStatusDto);
    }

    @PreAuthorize("hasRole('ADMIN') or hasRole('USER')")
    @GetMapping("/check-all")
    public BaseResponse<List<HostStatusDto>> getAllHostsStatus() {
        return BaseResponse.success(hostService.checkHostStatusAll());
    }
}
