package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

// 정형화된 패턴 컨트롤러-외부요청 받기 , 서비스-비즈니스 로직 처리 , 레포지토리-데이터 저장
// 위 3개 어노테이션 = @Component임 스프링빈을 등록하는 2가지 방법 중 하나인 컴포넌트 스캔 + 자동 의존관계 설정 방식임
// 다른 하나는 자바 코드로 직접 스프링 빈 등록하기. 스프링 빈 => 스프링 컨테이너 안에 존재하는 클래스 인스턴스(컴포넌트)??
@Controller // controller를 이렇게 만들어두면 실행시 스프링컨테이너에 해당 객체를 만들어서 갖고있음.
public class MemberController {

//    private final MemberService memberService = new MemberService();
    private final MemberService memberService;

    @Autowired // 스프링컨테이너에 있는 memberService를 가져와서 자동으로 연결시켜줌. 자동 주입해줄 클래스는 @Service 어노테이션으로 스프링에 인식시켜줘야함. DI 의존성 주입
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

}
