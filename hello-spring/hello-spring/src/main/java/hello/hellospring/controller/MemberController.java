package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

// 정형화된 패턴 컨트롤러-외부요청 받기 , 서비스-비즈니스 로직 처리 , 레포지토리-데이터 저장
// 위 3개 어노테이션 = @Component임 스프링빈을 등록하는 2가지 방법 중 하나인 컴포넌트 스캔 + 자동 의존관계 설정 방식임
// 다른 하나는 자바 코드로 직접 스프링 빈 등록하기. 스프링 빈 => 스프링 컨테이너 안에 존재하는 클래스 인스턴스(컴포넌트)??
// 직접 스프링빈 등록해도(SpringConfig에서) 컨트롤러는 어노테이션 남겨놔야함.
@Controller // controller를 이렇게 만들어두면 실행시 스프링컨테이너에 해당 객체를 만들어서 갖고있음.
public class MemberController {

//    private final MemberService memberService = new MemberService();
    private final MemberService memberService;

    @Autowired // 스프링컨테이너에 있는 memberService를 가져와서 자동으로 연결시켜줌. 자동 주입해줄 클래스는 @Service 어노테이션으로 스프링에 인식시켜줘야함. DI 의존성 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("members/new")
    public String create(MemberForm form) { // MemberForm 타입으로 form을 받으면 자동으로 MemberForm클래스내 setName으로 name 멤버 설정
        Member member = new Member();
        member.setName(form.getName());

//        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/"; // 회원가입 로직 끝나면 홈화면으로
    }

    @GetMapping("/members")
    public String list(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members);
        return "members/memberList";
    }
}
