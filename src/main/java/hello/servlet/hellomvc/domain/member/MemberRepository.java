package hello.servlet.hellomvc.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 동시성 뭔제가 고려되어 있지 않으므로, 실무에서는 ConcurrentHashMap, AtomicLong 사용을 고려한다.
public class MemberRepository {

    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;
    // static 이므로 모든 MemberRepository 객체가 같은 변수를 가진다.
    // MemberRepository를 싱글톤으로 구현했다면 static은 없어도 된다.

    private static final MemberRepository instance = new MemberRepository();
    // 싱글톤 구현을 위한 인스턴스 생성

    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){
    }
    // 외부에서 MemberRepository를 생성하지 못하도록 private으로 생성자를 만들어 외부 생성을 막는다.

    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll(){
        return new ArrayList<>(store.values());
        // store에 있는 모든 값들을 꺼내 ArrayList로 반환
        // store를 보호하기 위해 new ArrayList로 새 리스트를 만들어 반환한다.
    }

    public void clearStore(){
        store.clear();
    }
}
