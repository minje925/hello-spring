package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);   // member의 Id에 순서를 넣는다.
        store.put(member.getId(), member); // hashmap에 저장장
       return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // 저장소(map)에서 id를 가져와 리턴, null일 경우 처리하는 것
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()  // store를 루프를 돌면서 검사한다.
                .filter(member -> member.getName().equals(name)) // 입력받은 name과 저장소의 name이 같으면
                .findAny(); // 반환한다.
    }

    @Override
    public List<Member> findAll() {
        // 멤버들의 정보를 리스트로 변환하여 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear(); // store를 클리어 한다.
    }

}
