package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//// component scan(@Service등) 방식발고 자바코드로 직접 등록하려면 config 만들어줘야함.
//// 컨트롤러는 스캔방식이랑 똑같이 걍 냅둬 컨트롤러 클래스 아래 있는 Autowired도 냅둬..
@Configuration
public class SpringConfig {

    // jpa 사용할땐 아래 dataSource 필요없음.. entityManager 필요!
//    private DataSource dataSource;
//    // datasource 세팅
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    @PersistenceContext
//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    // dataJpa 사용을 위한 MemberRepository
    private final MemberRepository memberRepository;

    // 인젝션 받아서 멤버서비스에 등록해주면 dataJpa가 알아서..
    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    @Bean // 스프링 빈에 등록하라는 뜻
    public MemberService memberService() {
//        return new MemberService(memberRepository()); //MemberRepository 주입해줘야함. 생성자.. 여기선 아래 메서드로 주입
        return new MemberService(memberRepository); // 여기선 위에서 인젝션 받은 필드로..
    }

    @Bean
    public TimeTraceAop timeTraceAop() {
        return new TimeTraceAop();
    }


//    @Bean // 위에 멤버서비스에 생성자 파라미터로 주입해주기 위해 생성 메모리멤버리포가 구현체니까 리턴
//    public MemberRepository memberRepository() {
////        return new MemoryMemberRepository();
////        return new JdbcMemberRepository(dataSource); //Jdbc 사용 datasource 주입
////        return new JdbcTemplateMemberRepository(dataSource); //JdbcTemplate 사용
////        return new JpaMemberRepository(em);
//        return
//    }
}
