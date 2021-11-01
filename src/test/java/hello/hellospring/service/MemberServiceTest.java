package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    /*
    MemberService memberService = new MemberService();
    MemberRepository memberRepository = new MemoryMemberRepository();
    // 이렇게 하면 다른 객체이기 때문에 다른 리포지토리를 접근 한다.
    */

    MemberService memberService;
    MemberRepository memberRepository;
    @BeforeEach
    public void beforeEach() {
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    // memberService의 생성자를 통해 같은 리포지토리를 할당해주었다. => DI라고 한다.
    @Test
    void join() {
        // given
        Member member1 = new Member();
        member1.setName("minje");

        // when
        Long result = memberService.join(member1);

        // then
        Member findMember = memberService.findOne(result).get();
        assertThat(member1.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void checkDuple() {
        // given
        Member member1 = new Member();
        member1.setName("minje");

        Member member2 = new Member();
        member2.setName("minje");

        // when
        // 예외가 잘 동작하는지 체크하는 구문
        /*
        memberService.join(member1);
        try {
            memberService.join(member2);
            fail();
        } catch (IllegalStateException e) {
            assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
        }
        */
        // 위 아래 같은 예외를 검사하는 예외구문이다.
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class,
                () -> memberService.join(member2));//예외가 발생해야 한다.
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 이름입니다.");
    }
    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}