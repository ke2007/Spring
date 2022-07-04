package hello.hellospring;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration //스프링이 configuration을 읽고
public class SpringConfig {

    @Bean //스프링 bean에 등록하라는 뜻이구나~ 하고 인식해서
    public MemberService memberService() { //memberService를
        return new MemberService(memberRepository()); //로직을 호출해서 등록
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
