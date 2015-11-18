package ch05;

import javax.persistence.*;

/**
 * Created by we on 2015. 11. 16..
 */
@Entity
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String id;
    private String name;

    @ManyToOne                      // 다대일 관계라는 매핑 정보, 연관관계 매핑시 다중성을 나타내는 어노테이션을 필수로 사용해야 함
    @JoinColumn(name = "TEAM_ID")   // 외래 키를 매핑할 때 사용, 외래 키 이름 지정
    private Team team;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setTeam(Team team) {
        this.team = team;
        team.getMembers().add(this);    // 양방향 관계를 설정하도록 함
    }

    public Team getTeam() {
        return team;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
