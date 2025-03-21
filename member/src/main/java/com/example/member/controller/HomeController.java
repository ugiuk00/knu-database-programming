package com.example.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/") // getmapping시 static/index.html 보다 우선적으로 실행
    public String home(){

        return "home"; //컨트롤러 및 겟매핑 어노테이션 사용시, 반환값은 뷰의 이름으로 인식 -> String 형식
    }
}
