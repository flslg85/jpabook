package jpabook.start;

import javax.persistence.*;  //**
import java.util.Date;

/**
 * User: HolyEyE
 * Date: 13. 5. 24. Time: 오후 7:43
 *
 * 요구사항 추가
 * 1. 회원은 일반 회원과 관리자로 구분해야 한다
 * 2. 회원 가입일과 수정일이 있어야 한다
 * 3. 회원을 설명할 수 있는 필드가 있어야 한다( 이 필드는 길이 제한이 없다 )
 * 4. 회원 이름은 필수로 입력되어야 하고 10자를 초과하면 안 된다
 */
@Entity
@Table(name="MEMBER")
public class Member {

    @Id
    @Column(name = "ID")
    private String id;

    @Column(name = "NAME", nullable = false, length = 10)  // 조건 추가
    private String username;

    private Integer age;

    // 추가
    @Enumerated(EnumType.STRING)    // 자바의 enum을 사용하려면 @Enumerated 어노테이션으로 매핑해야 한다
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)   // 날짜 타입을 매핑
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob    // BLOB, CLOB 타입을 매핑 / 회원을 설명하는 필드는 길이 제한이 없으므로 CLOB 타입으로 저장해야 함
    private String description;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
