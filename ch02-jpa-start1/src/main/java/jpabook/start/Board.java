package jpabook.start;

import javax.persistence.*;

/**
 * Created by flslg85 on 2015. 11. 12..
 */
@Entity
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String data;

    public Long getId() {
        return id;
    }

    public void setData(String data) {
        this.data = data;
    }
}
