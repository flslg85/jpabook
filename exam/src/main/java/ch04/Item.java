package ch04;

import javax.persistence.*;

/**
 * Created by we on 2015. 11. 17..
 */
@Entity
public class Item {
    @Id
    @GeneratedValue
    @Column(name = "ITEM_ID")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;
}
