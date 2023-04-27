package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//이건 인터페이스니까 여기말고 구현체에서 리포지토리 어노테이션 사용
public interface MemberRepository {
    Member save(Member member);
    Optional<Member> findById(Long id); // null값을 optional을 통해 감싸서 반환
    Optional<Member> findByName(String name);
    List<Member> findAll();
}
