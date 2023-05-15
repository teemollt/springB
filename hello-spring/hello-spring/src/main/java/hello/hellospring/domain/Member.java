package hello.hellospring.domain;

import javax.persistence.*;

// jpa 사용, jpa가 관리하는 entity
@Entity
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // db에서 pk값을 자동으로 생성해주는 경우 이렇게 씀??
    private Long id;

//    @Column(name = "username") // db상의 컬럼 이름과 해당 멤버를 매핑
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
