package com.shop.controller;

import com.shop.dto.MemberFormDto;
import com.shop.entity.Member;
import com.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/members")
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping(value = "/new")
    public String memberForm(Model model){ // 회원가입 페이지로 이동할 수 있도록 MemberController 클래스에 메서드 작성
        model.addAttribute("memberFormDto", new MemberFormDto());
        return "member/memberForm";
    }

   @PostMapping(value = "/new")
   public String newMember(@Valid MemberFormDto memberFormDto, BindingResult bindingResult, Model model){ // 검증하려는 객체에 Valid 선언하고 BindingResult 객체 추가

        if(bindingResult.hasErrors()){ // 검사후 결과는 bindingResult 에 담아주고 bindingResult.hasErrors()를 호출하여 에러가 있다면 회원가입 페이지로 이동
            return "member/memberForm";
       }

       try {
            Member member = Member.createMember(memberFormDto, passwordEncoder);
            memberService.saveMember(member);
       } catch (IllegalStateException e){
           model.addAttribute("errorMessage", e.getMessage()); // 회원가입시 중복 회원 가입 예외가 발생하면 에러메시지가 뷰로 전달
           return "member/memberForm";
       }

       return "redirect:/";
   }


}