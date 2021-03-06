package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

 //스프링에서 서비스를 컨테이너에 등록해줌
public class MemberService {
    private final MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;

    }
//    @Service //스프링에서 서비스를 컨테이너에 등록해줌
//    public class MemberService {
//        private final MemberRepository memberRepository;
//        @Autowired
//        public MemberService(MemberRepository memberRepository) {
//            this.memberRepository = memberRepository;
//
//        }

    /**
     * 회원가입
     */
    public Long join(Member member) {

        long start = System.currentTimeMillis();


        try {
            validateDuplicateMember(member); //중복회원 검증 메서드

            memberRepository.save(member);
            return member.getId();

        } finally { //항상들어옴
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */

    public List<Member> findMembers() {
        long start = System.currentTimeMillis();

        try {
            return memberRepository.findAll();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("findMembers = " + timeMs + "Ms");
        }

    }


    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
