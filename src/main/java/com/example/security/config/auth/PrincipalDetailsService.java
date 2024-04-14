package com.example.security.config.auth;

import com.example.security.model.User;
import com.example.security.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//시큐리티 설정에서 loginProcssingUrl("/login");
// /login 요청이 오면 자동으로 UserDetailsService 타입으로 ioc되어 있는 loadUserByUsername 함수가 실행
@Service
@RequiredArgsConstructor
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    //String username 머스테치에 name하고 같아야 한다
    //시큐리티 session(내부 Authentication(내부UserDetails))
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("username:"+username);
        User userEntity = userRepository.findByUsername(username);
        if (userEntity != null){
            return new PrincipalDetails(userEntity);
        }
        return null;
    }
}
