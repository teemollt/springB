package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    // ctrl+shift+t => test class 생성
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) { // 내부에서 인스턴스 생성X 외부에서 넣어줘
        // 멤버서비스테스트 클래스 참고
        this.memberRepository = memberRepository;
    }
    // 테스트에서 문제점 해결 위해서 위에서 바로 인스턴스 생성하는게 아니라 변수만 만들어놓고 아래 작업.


    /**
     * 회원 가입
     * @param member
     * @return
     */
    public Long join(Member member) {
//        // 같은 이름이 있는 중복 회원 가입 불가
//        Optional<Member> rst = memberRepository.findByName(member.getName());
//        // Optional의 메서드를 활용해서 아래와 같이 익셉션 던지기.
//        rst.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        }); ctrl+alt+shift+t -> 메서드 추출 통해서 따로 메서드로 아래쪽에 분리
        validateDuplicateMember(member); // 중복 회원 검증 중복일시 exception throw됨.
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                        .ifPresent(m -> {
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                        });
    }

    /**
     *  전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}