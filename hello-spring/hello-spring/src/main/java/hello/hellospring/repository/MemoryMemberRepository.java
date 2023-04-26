package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //Optional.ofNullable() => 가져오는 값이 null 이어도 ㄱㅊ
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()  // 배열 컬렉션을 함수형으로 처리가능.
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    // test 코드에서 afterEach 콜백 메서드에서 각 테스트 케이스를 수행할때마다 클리어 해주기위해 작성.
    // 안해주면 각 테스트 케이스에서 생성했던 객체나 데이터들이 영향을 미쳐서 문제 생김.
    public void clearStore() {
        store.clear();
    }

}
