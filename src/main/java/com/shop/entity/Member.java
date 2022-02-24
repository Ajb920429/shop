package com.shop.entity;

import com.shop.constant.Role;
import com.shop.dto.MemberFormDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name="member")
@Getter @Setter
@ToString
public class Member extends BaseEntity { // 회원정보를 저장하는 엔티티

    @Id
    @Column(name="member_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(unique = true) //회원은 이메일을 통해 유일하게 구분해야 하기 때문에, 동일한 값이 데이터 베이스에 들어올 수 없도록 유니크 속성을 지정
    private String email;

    private String password;

    private String address;

    @Enumerated(EnumType.STRING) // 자바의 enum 타입을 엔티티 속성으로 지정 또한 순서가 바뀔경우 문제가 발생 할 수 있으므로 EnumType.STRING 옵션을 통해 String 으로 저장하기 권장함
    private Role role;

    public static Member createMember(MemberFormDto memberFormDto, PasswordEncoder passwordEncoder){
        Member member = new Member();
        member.setName(memberFormDto.getName());
        member.setEmail(memberFormDto.getEmail());
        member.setAddress(memberFormDto.getAddress());
        String password = passwordEncoder.encode(memberFormDto.getPassword()); // BCryptPasswordEncoder 으로 넘겨서 비밀번호 암호화
        member.setPassword(password);
        member.setRole(Role.ADMIN);
        return member;
    }

}
