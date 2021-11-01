package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

public class MemoryMemberRepositoryTest {
    MemberRepository repository = new MemoryMemberRepository();

    @AfterEach // 메소드가 실행이 끝날 때마다 실행되는 것
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    public void save() {
        Member member = new Member();
        member.setName("minje");

        repository.save(member);
        Member result = repository.findById(member.getId()).get(); // return값이 Optional이기 때문에 get()으로 꺼낼 수 있다.

        System.out.println("result : = "+(result == member));
        // 내가 입력한 정보와 메모리에 저장된 정보가 똑같은가?
        Assertions.assertThat(member).isEqualTo((result));
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("minje");
        repository.save(member1);

        Member member2 = new Member(); // shift+F6 하면 rename 가능
        member2.setName("gildong");
        repository.save(member2);

        Member result = repository.findByName("minje").get();
        // 메모리에서 minje의 멤버 정보를 찾아서
        Assertions.assertThat(result).isEqualTo(member1);
        // member1과 비교한다.
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("minje");
        repository.save(member1);

        Member member2 = new Member(); // shift+F6 하면 rename 가능
        member2.setName("gildong");
        repository.save(member2);

        List<Member> result = repository.findAll();
        // findlAll의 반환 값은 2개 길이의 리스트
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
