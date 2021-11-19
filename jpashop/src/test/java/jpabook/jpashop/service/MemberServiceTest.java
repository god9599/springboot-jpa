package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @DisplayName("회원 가입 >> 성공")
    @Test
    public void join() throws Exception {
        //given
        Member member = new Member();
        member.setName("Kim");

        //when
        Long savedId = memberService.join(member);

        //then
        assertEquals(savedId, memberRepository.findOne(savedId).getId());
    }

    @DisplayName("회원 이름 중복 예외 >> 성공")
    @Test
    public void duplicate_exception() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);

        //then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }

    @DisplayName("회원 전체 조회 >> 성공")
    @Test
    public void findAllMember() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim1");

        Member member2 = new Member();
        member2.setName("kim2");

        memberService.join(member1);
        memberService.join(member2);

        // when
        List<Member> members = memberService.findMembers();

        assertEquals("kim1", members.get(0).getName());
        assertEquals("kim2", members.get(1).getName());
    }

    @DisplayName("회원 한 명 조회 >> 성공")
    @Test
    public void findOneMember() throws Exception{
        //given
        Member member = new Member();
        member.setName("kim");

        Long joinedId = memberService.join(member);

        //when
        Member member1 = memberService.findMember(joinedId);

        //then
        assertEquals(joinedId, member1.getId());
    }
}