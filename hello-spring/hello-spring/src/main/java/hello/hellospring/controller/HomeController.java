package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 웹브라우저에서 요청이 오면
    // 1. 스프링 컨테이너에서 관련 컨트롤러가 있는지 확인
    // 2. 없으면 static 파일을 확인함.
    // 따라서 아래같이 기본 url에 getmapping을 하고 특정 html을 리턴해주면 static의 index보다 우선해서 해당 리턴값을 응답함
    @GetMapping("/")
    public String home() {
        return "home";
    }
}
