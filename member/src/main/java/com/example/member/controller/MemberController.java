package com.example.member.controller;

import com.example.member.domain.Member;
import com.example.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(MemberForm form) {
        System.out.println("******회원가입 폼 실행 시작*********");
        System.out.println("입력값= " + form.getName());
        Member member = new Member();
        member.setName(form.getName());
        System.out.println("member.id =" + member.getId());
        System.out.println("member.name=" + member.getName());
        memberService.join(member);
        return "redirect:/";
    }
}
