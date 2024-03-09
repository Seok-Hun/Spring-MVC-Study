package hello.servlet.domain.member;

import hello.servlet.hellomvc.domain.member.Member;
import hello.servlet.hellomvc.domain.member.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemberRepositoryTest {
// JUnit 5부터는 class의 public이 없어도 된다.

    MemberRepository memberRepository = MemberRepository.getInstance();

    @AfterEach
    void afterEach(){
    // 테스트가 끝날 때마다 저장소 초기화
        memberRepository.clearStore();
    }

    @Test
    void save(){

        // given
        Member member = new Member("hello",20);

        // when
        Member saveMember = memberRepository.save(member);

        // then
        Member findMember = memberRepository.findById(saveMember.getId());
        Assertions.assertThat(findMember).isEqualTo(saveMember);
        // 저장한 Member와 find()로 찾은 Member가 일치하는지 확인한다.
    }

    @Test
    void findAll(){

        // given
        Member member1 = new Member("member1", 20);
        Member member2 = new Member("member2", 30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        // when
        List<Member> all = memberRepository.findAll();

        // then
        Assertions.assertThat(all.size()).isEqualTo(2);
        // 저장소에 저장한 member가 2개가 맞는지 확인
        Assertions.assertThat(all).contains(member1,member2);
        // 저장소에 저장한 member에 member1,member2가 포함되어 있는지 확인
    }
}
