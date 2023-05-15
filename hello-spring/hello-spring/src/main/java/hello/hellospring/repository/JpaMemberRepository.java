package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    // jpa를 사용하려면 entityManager를 주입 받아야함.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); // db에 영구 저장 메서드 jpa가 자동으로 insert문 만들어서 넣어줌. setId까지..
        return member;
    }

    // pk 기반이므로 간단하게 메서드로 해결.
    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    // pk 기반이 아닌 것들은 jpql을 작성해줘야함 jpql은 아래 sql 비슷한거.. 객체, entity를 select 가능..
    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        // 아래처럼 결국 메서드 실행 리턴값을 최종적으로 리턴할때 합치면 간단해지겠지? ctl+alt+n 인라인 단축키
        // 아래 두줄을 그아래 한줄로..
//        List<Member> result = em.createQuery("select m from Member m", Member.class).getResultList();
//        return result;
        return em.createQuery("select m from Member m", Member.class).getResultList();
        // jpa 에서는 위처럼 객체 자체를 select 할수 있음. Member라는 entity select
    }
}
