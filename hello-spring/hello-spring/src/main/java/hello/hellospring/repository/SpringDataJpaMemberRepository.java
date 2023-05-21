package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // 인터페이스 다중상속


    // JpaRepository 여기서 공통화가 안된 인터페이스 메서드는 여기서 따로 재정의를 해줘야하는데
    // 네이밍 규칙에 따라 findByName은 아래와 같은 JPQL로 변환된다
    // select m from Member m where m.name = ?
    @Override
    public Optional<Member> findByName(String name);

    // 메서드 이름을 findByNameAndId 나 findByNameOrId와 같이 지으면 알아서 JPQL이 수행됨 규칙 알겠지..?
    // 이런식으로 springDataJpa 는 인터페이스 메서드 네이밍만으로 간단한 기능들 구현 가능 페이징까지..
    // JPA와 springDataJpa를 기본으로 사용하고 복잡한 동적쿼리는 queryDsl을 사용해 안전하게 자바 코드로 작성 가능,
    // 이걸로도 안되면 네이티브 쿼리(직접 sql) 사용하거나 jdbcTemplate을 섞어서 사용할 수 있음.
}
