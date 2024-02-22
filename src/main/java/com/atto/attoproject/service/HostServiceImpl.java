package com.atto.attoproject.service;

import com.atto.attoproject.config.exception.error.CustomException;
import com.atto.attoproject.data.HostDto;
import com.atto.attoproject.data.HostStatusDto;
import com.atto.attoproject.domain.Host;
import com.atto.attoproject.domain.HostStatus;
import com.atto.attoproject.repository.HostRepository;
import com.atto.attoproject.repository.HostStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class HostServiceImpl implements HostService {
    private final HostRepository hostRepository;
    private final HostStatusRepository hostStatusRepository;

    @Transactional
    @Override
    public Host registerHost(HostDto hostDto) {
        validateUniqueNameAndIp(hostDto);
        limitDevices100();
        Host saveHost = hostRepository.save(Host.of(hostDto));

        HostStatus hostStatus = HostStatus.from(saveHost);
        hostStatusRepository.save(hostStatus);
        return saveHost;
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
                .orElseThrow(() -> CustomException.of("404", "host 값을 찾을수 없습니다.", HttpStatus.BAD_REQUEST));
        host.update(hostDto);
        return hostRepository.save(host);
    }

    @Transactional
    @Override
    public void delete(Long id) {
        hostRepository.findById(id)
                .orElseThrow(() -> CustomException.of("404", "host 값을 찾을수 없습니다.", HttpStatus.BAD_REQUEST));
        hostRepository.deleteById(id);
    }

    @Transactional
    @Override
    public HostStatusDto checkHostStatus(Long id) {
        HostStatus hostStatus = hostStatusRepository.findById(id)
                .orElseThrow(() -> CustomException.of("404", "host 값을 찾을수 없습니다.", HttpStatus.BAD_REQUEST));
        hostStatus.updateStatus();
        return hostStatusRepository.findByCheckId(id);
    }

    @Transactional
    @Override
    public List<HostStatusDto> checkHostStatusAll() {
        List<HostStatusDto> hostStatuses = hostStatusRepository.findByAllHostAndHostsStatus();

        // ExecutorService 생성 (적절한 스레드 풀 크기 설정)
        ExecutorService executorService = Executors.newCachedThreadPool();

        // CompletableFuture.supplyAsync 사용하여 비동기 실행
        List<CompletableFuture<HostStatusDto>> futures = hostStatuses.stream()
                .map(hostStatusDto -> CompletableFuture.supplyAsync(() -> checkHostStatus(hostStatusDto), executorService))
                .collect(Collectors.toList());
        try {

            return CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]))
                    .thenApply(v -> futures.stream()
                            .map(CompletableFuture::join)
                            .collect(Collectors.toList()))
                    .get();
        } catch (InterruptedException | ExecutionException e) {
            //e.printStackTrace();
            throw CustomException.of("400","호스트 상태 정보를 가져오는 중 오류 발생", HttpStatus.BAD_REQUEST);
        } finally {
            // ExecutorService 종료
            executorService.shutdown();
        }

    }

    @Override
    public List<HostStatusDto> getAllHostsStatus() {
        return hostStatusRepository.findByAllHostAndHostsStatus();
    }

    public HostStatusDto checkHostStatus(HostStatusDto hostStatusDto) {
        hostStatusDto.updateStatus(); // 상태 업데이트 메소드 호출
        return hostStatusDto;
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
