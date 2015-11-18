package ch05;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by we on 2015. 11. 16..
 */
@Entity
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private String id;

    private String name;

    @OneToMany(mappedBy = "team")   //  mappedBy 양방향 매핑시 사용 반대쪽 매핑의 필드 이름 값으로 설정
    private List<Member> members = new ArrayList<Member>();

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
