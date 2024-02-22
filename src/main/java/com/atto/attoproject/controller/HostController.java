package com.atto.attoproject.controller;

import com.atto.attoproject.config.basedto.BaseResponse;
import com.atto.attoproject.config.exception.error.CustomException;
import com.atto.attoproject.data.HostStatusDto;
import com.atto.attoproject.data.request.HostRequest;
import com.atto.attoproject.data.HostDto;
import com.atto.attoproject.domain.Host;
import com.atto.attoproject.domain.HostStatus;
import com.atto.attoproject.service.HostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
//@PreAuthorize("hasRole('ADMIN')")
@RequestMapping("/api/hosts")
public class HostController {
    private final HostService hostService;

    @PostMapping
    public HostDto registerHost(@Valid @RequestBody HostRequest hostRequest){
        Host host = hostService.registerHost(hostRequest.toDto());
        return HostDto.of(host);
    }

    @GetMapping("/list")
    public List<HostDto> getAllHosts() {
        List<Host> hostList = hostService.getAllHosts();
        return hostList.stream().map(HostDto::of).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public HostDto getHostById(@PathVariable("id") Long id) {
        return HostDto.of(hostService.findById(id));
    }

    @PutMapping("/{id}")
    public Host updateHost(@PathVariable("id") Long id, @Valid @RequestBody HostRequest hostRequest) {
        return hostService.updateHost(id, hostRequest.toDto());
    }

    @DeleteMapping("/{id}")
    public BaseResponse<?> deleteHost(@PathVariable("id") Long id) {
        hostService.delete(id);
        return BaseResponse.successMessage();
    }

    @GetMapping("/check/{id}")
    public BaseResponse<HostStatusDto> checkHost(@PathVariable("id") Long id) {
        HostStatusDto hostStatusDto = hostService.checkHostStatus(id);
        return BaseResponse.success(hostStatusDto);
    }

    @GetMapping("/check-all")
    public BaseResponse<List<HostStatusDto>> getAllHostsStatus() {
        return BaseResponse.success(hostService.checkHostStatusAll());
    }
}
