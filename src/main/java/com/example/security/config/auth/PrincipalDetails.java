package com.example.security.config.auth;

//시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다
//로그인을 진행이 완료가 괴면 시큐리티 session에 만들어준다 (Security ContextHolder)
//오브젝트 타입=> Authentication 타입 객체
//Authentication 안에 User 정보가 있어야 됨
// User 오브젝트 타입 => UserDetails 타입 객체

//Security Session => Authentication => UserDetails(PrincipalDetails)

import com.example.security.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private User user; //콤포지션

    //생성자
    public PrincipalDetails(User user){
        this.user=user;
    }

    //해당 유저의 권한을 리턴하는 곳
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collection =new ArrayList<>();
        collection.add(new GrantedAuthority() {

            //String 을 넣을수 있다
            @Override
            public String getAuthority() {
                return user.getRole();
            }

        });
        return collection;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override //계정 만료
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override // 계정 잠겼니
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override //이걔정의 비밀번호 유효기간이 지났니
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override //계정이 활성화 되었니
    public boolean isEnabled() {

        //우리 사이트!! 1년동안 회원이 로그인을 안하면 휴먼 계정이
        //현재시간 - 로긴시간 => 1년을 초과하면 return false

        return true;
    }
}