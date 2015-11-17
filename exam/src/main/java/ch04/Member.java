package ch04;

import javax.persistence.*;

/**
 * Created by we on 2015. 11. 17..
 */
@Entity
public class Member {
    @Id
    @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String name;
    private String city;
    private String street;
    private String zipcode;
}
