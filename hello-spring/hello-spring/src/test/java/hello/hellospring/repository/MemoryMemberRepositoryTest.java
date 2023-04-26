package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

// 이런 테스트 클래스를 먼저 만들고 여기에 맞게 구현 클래스를 만들면 TDD 테스트 주도 개발임..
public class MemoryMemberRepositoryTest {   // class level에서도 테스트 가능 패키지 단위에서도 가능.

    MemoryMemberRepository repository = new MemoryMemberRepository();

    // 테스트는 서로 순서에 의존관계 없이 설계가 되어야함 따라서 아래 afterEach 필요함.
    // 하나의 테스트가 끝날때마다 공용 저장소나 공용 데이터를 지워줘야함.
    @AfterEach // 아래 각 테스트들이 끝날때마다 실행되는 콜백 메서드
    public void afterEach() {
        repository.clearStore(); // 아래 테스트 수행이 끝날때마다 저장소를 다 클리어 해줌
        // MemoryMemberRepository 에서 메서드 정의해놨음.
    }

    @Test
    public void save() {
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        // findById 메서드는 optional 이므로 값을 get()으로 꺼낼수 있음.

//        System.out.println("rst = " + (result == member));
//        Assertions.assertEquals(member, null); // assertEquals(기대값, 실제값);

        assertThat(member).isEqualTo(result);
        // alt+enter후 static import하면 Assertions 클래스 안쓰고 바로 assertThat등등메서드 사용가능
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //shift+f6 rename기능 쓰면 한번에 바뀜 아래것두
        member2.setName("spring2");
        repository.save(member2);

        Member rst = repository.findByName("spring2").get(); //get()을 쓰면 option으로 감싸진걸 꺼낼수 있는듯

        assertThat(rst).isEqualTo(member2);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //shift+f6 rename기능 쓰면 한번에 바뀜 아래것두
        member2.setName("spring2");
        repository.save(member2);

        List<Member> rst = repository.findAll();

        assertThat(rst.size()).isEqualTo(2);

    }

  }
