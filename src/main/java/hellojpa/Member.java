package hellojpa;

import javax.persistence.*;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

//    관계형 DB 관점에서 본다면 TEAM 테이블의 PK인 TEAM_ID를 외래키로하기 때문에 필드를 teamId로 하는 것이 맞다.
//    하지만 코드가 객체지향적이지 않다. 또한 JPA를 사용해야하는 이유가 없어진다.
//    @Column(name = "TEAM_ID")
//    private Long teamId;

    /**
     *     Member와 Team은 다대일 관계이다. Member 여러 명이 하나의 팀에 소속될 수 있기 때문이다.
     *     다대일 관계에서는 @ManyToOne 애노테이션을 사용한다.
     *     Team 테이블의 PK를 외래키로 사용할 생각이기 때문에 TEAM_ID컬럼을 Join하는 @JoinColumn 애노테이션을 사용
     *     정리 : 연관관계가 어떻게 되는지 작성(@ManyToOne), Join하는 기준이 되는 컬럼을 설정(TEAM_ID)
      */

    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Team getTeam() {
        return team;
    }

    // 양방향 연관관계 편의 메서드 : Member 객체에서 setTeam으로 team 데이터를 추가할 때 자동으로 team 객체에도 데이터값을 추가한다.
    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);
    }
}
