package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class JpaMemberRepository implements MemberRepository{

    private final EntityManager em; //JPA는 Entitymaneger로 모든게 동작한다. DB커넥션 정보들이랑 application properties에있는 정보들을 다 들고있다

    public JpaMemberRepository(EntityManager em) {
        this.em = em; //만들어진걸 인젝션받는다
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //데이터를 저장한다 JPA가 DB에 insert쿼리를 만들어서 날려줌
        return member;
    }

    @Override
    public Optional<Member> findById(long id) {
        Member member = em.find(Member.class, id);
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findAny();
    }
    @Override
    public List<Member> findAll() { //하나만 찾는건find만 쓰면되지만 그외의 경우는 jpql을 짜야한다.
        return em.createQuery("select m from Member m", Member.class) //Member entity 객체 m 자체를 select 해라
                .getResultList();
    }
}
