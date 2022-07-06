package hello.hellospring.domain;

import javax.persistence.*;

@Entity //아 이건 JPA가 관리하는 엔티티구나
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //이건 Id야~ / db가 알아서 생성하는 id는 IDENTITY라고함
    private long id;

    @Column() //DB의 컬럼명이 만약 username이면 이렇게 db에있는 username에 매핑이됨.
    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
