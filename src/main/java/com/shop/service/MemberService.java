
package com.shop.service;

import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional // 로직처리하다가 에러발생시 이전상태로 콜백시켜줌
@RequiredArgsConstructor //
public class MemberService implements UserDetailsService { // MemberService 가 UserDetailsService 를 구현

    private final MemberRepository memberRepository;

    public Member saveMember(Member member){
        validateDuplicateMember(member);
        return memberRepository.save(member);
    }

    private void validateDuplicateMember(Member member){ // 이미 가입된경우 IllegalStateException 예외처리를 발생시켜줌
        Member findMember = memberRepository.findByEmail(member.getEmail());
        if(findMember != null){
            throw new IllegalStateException("이미 가입된 회원입니다.");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // 로그인할 유저의 이메일을 파리머토로 전달답습니다

        Member member = memberRepository.findByEmail(email);

        if(member == null){
            throw new UsernameNotFoundException(email);
        }

        return User.builder() // UserDetail을 구현하고 있는 User 객체를 반환해줍니다. User 객체를 생성하기 위해서 생성자로 회원의 이메일 비밀번호 role을 파라미터로 넘겨줌
                .username(member.getEmail())
                .password(member.getPassword())
                .roles(member.getRole().toString())
                .build();
    }

}

//package com.shop.service;
//
//import com.shop.entity.Member;
//import com.shop.repository.MemberRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//@Service
//@Transactional // 로직처리하다가 에러발생시 이전상태로 콜백시켜줌
//@RequiredArgsConstructor //
//public class MemberService implements UserDetailsService { // MemberService 가 UserDetailsService 를 구현
//
//    private final MemberRepository memberRepository;
//
//    public Member saveMember(Member member){
//        validateDuplicateMember(member);
//        return memberRepository.save(member);
//    }
//
//    private void validateDuplicateMember(Member member){ // 이미 가입된경우 IllegalStateException 예외처리를 발생시켜줌
//        Member findMember = memberRepository.findByEmail(member.getEmail());
//        if(findMember != null){
//            throw new IllegalStateException("이미 가입된 회원입니다.");
//        }
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException { // 로그인할 유저의 이메일을 파리머토로 전달답습니다
//
//        Member member = memberRepository.findByEmail(email);
//
//        if(member == null){
//            throw new UsernameNotFoundException(email);
//        }
//
//        return User.builder() // UserDetail을 구현하고 있는 User 객체를 반환해줍니다. User 객체를 생성하기 위해서 생성자로 회원의 이메일 비밀번호 role을 파라미터로 넘겨줌
//                .username(member.getEmail())
//                .password(member.getPassword())
//                .roles(member.getRole().toString())
//                .build();
//    }
//
//}


