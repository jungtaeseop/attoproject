package com.atto.attoproject.service;

import com.atto.attoproject.config.exception.CustomException;
import com.atto.attoproject.data.HostDto;
import com.atto.attoproject.domain.Host;
import com.atto.attoproject.repository.HostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;

    @Transactional
    @Override
    public Host registerHost(HostDto hostDto) {
        validateUniqueNameAndIp(hostDto);
        limitDevices100();
        Host host = Host.of(hostDto);
        return hostRepository.save(host);
    }

    @Override
    public List<Host> getAllHosts() {
        return hostRepository.findAll();
    }

    @Override
    public Host findById(Long id) {
        return hostRepository.findById(id)
                .orElseThrow(() -> CustomException.of("404", "host 찾을수 없습니다.", HttpStatus.BAD_REQUEST));
    }

    @Transactional
    @Override
    public Host updateHost(Long id, HostDto hostDto) {
        validateUniqueNameAndIp(hostDto);
        Host host = hostRepository.findById(id)
                .orElseThrow(() -> CustomException.of("404", "host 찾을수 없습니다.", HttpStatus.BAD_REQUEST));
        host.update(hostDto);
        return hostRepository.save(host);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        hostRepository.findById(id)
                .orElseThrow(() -> CustomException.of("404", "host 찾을수 없습니다.", HttpStatus.BAD_REQUEST));
        hostRepository.deleteById(id);
    }

    private void validateUniqueNameAndIp(HostDto hostDto) {
        if (hostRepository.existsByName(hostDto.getName())) {
            throw CustomException.of("409", "중복된 호스트 이름입니다.", HttpStatus.CONFLICT);
        }
        if (hostRepository.existsByIp(hostDto.getIp())) {
            throw CustomException.of("409", "중복된 ip 입니다.", HttpStatus.CONFLICT);
        }
    }

    private void limitDevices100() {
        if (hostRepository.count() >= 100) {
            throw CustomException.of("400", "호스트 등록은 100개로 제한 됩니다.", HttpStatus.BAD_REQUEST);
        }
    }
}
