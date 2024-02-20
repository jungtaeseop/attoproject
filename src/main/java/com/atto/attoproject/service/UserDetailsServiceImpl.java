package com.atto.attoproject.service;

import com.atto.attoproject.domain.User;
import com.atto.attoproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        User user = userRepository.findByUserId(userId)
                .orElseThrow(() -> new UsernameNotFoundException("userid가 있는 사용자를 찾을 수 없습니다: " + userId));

        return UserDetailsImpl.build(user);
    }
}
