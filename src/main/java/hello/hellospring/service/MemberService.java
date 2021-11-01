package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {
    //private final MemberRepository memberRepository = new MemoryMemberRepository();

    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입 예제
    public Long join(Member member) {
        // 같은 이름이 있는 중복회원이 불가능하다고 가정하면
        checkDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }
    // 모든 회원 존재
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    // memberID로 회원 조회
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }

    private void checkDuplicateMember(Member member) {
        Optional<Member> result = memberRepository.findByName(member.getName());
        // memberRepository.findByName(member.getName()); 입력 후 Ctrl+Alt+v 하면 리턴값이 자동 설정됨
        // result.get()
        /* null이 포함될 수 있는 값을 Optional로 감싸서 리턴해서 받았기 때문에
            Optional의 함수를 사용할 수 있다.
            ifPresent() 함수는 : 값이 있으면 m에 그 값을 넣어준다. 그리고 아래의 함수를 실행한다.
            */
        result.ifPresent(m-> {
            throw new IllegalStateException("이미 존재하는 이름입니다.");
        });
    }
}
