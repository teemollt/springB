package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "컨트롤러 어트리뷰트 밸류 hello!!!");
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam(value = "name") String name, Model model) {
        model.addAttribute("name", name);
        if (name.length() < 4) {
            return "hello-template";
        }
        return "hi/hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody // http body에 리턴하는 데이터를 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name) {
        return "hello " + name;
    }

    // 아래 처럼 객체로 넘겨주면 json 형태로 데이터가 넘어감
    // 최근 플젝은 이런식으로 해주는게 많음..
    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;

    }

    // 객체로 넘겨주려면 아래와 같이 객체를 만들어줘야지.
    // 변수를 private으로 선언하고 외부에서는 getter/setter로만 접근가능하게 만듬 javabean 방식
    static class Hello {
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
