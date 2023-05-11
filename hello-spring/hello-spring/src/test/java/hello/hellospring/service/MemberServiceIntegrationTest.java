package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional // 이거 "테스트 클래스에 써주면" 테스트 끝나고 롤백해줌. db에 데이터가 남지않아 다음 테스트에 영향x
class MemberServiceIntegrationTest {


    @Autowired MemberService memberService; // Test코드니까 그냥 편하게 autowired로 바로 필드를 받아서 사용해도 무방
    @Autowired MemberRepository memberRepository;

    @Test
    void 회원가입() {// 한글로 메서드명 지어도 무방 test코드는 빌드할때 포함이 안되니까..
        //아래 3단계 패턴으로 test 코드 짜면 좋은듯
        // given 뭔가 주어졌을때
        Member member = new Member();
        member.setName("ho");

        //when 이걸 실행했을때
        Long saveId = memberService.join(member);

        //then 결과가 이게 나와야해!
        Member findMember = memberService.findOne(saveId).get(); // ctrl+alt+v 메서드의 반환값을 변수에 넣을 수 있게 타압 변수명 = 세팅해주는 단축키
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    // Test는 예외 확인이 더 중요하다!!
    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("ho");

        Member member2 = new Member();
        member2.setName("ho");

        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다."); // message까지 검증하고싶을때 이렇게.
        // 2번째 파라미터로 넣은 람다함수를 실행하면 1번째 파라미터에 넣은 예외가 터져야함. 그래야 성공.

// try catch보다 위에 방식 사용??
//        try {
//            memberService.join(member2);//member1과 name이 같으므로 서비스에서 로직짠대로 에러가 나야함.
//            fail(); // 윗줄에서 에러가 나는게 정상인데 안나서 여기로 왔다면 fail
//        } catch (IllegalStateException e) {
//            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }



        //then

    }

}