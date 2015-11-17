package ch04;

import javax.persistence.*;

/**
 * Created by we on 2015. 11. 17..
 */
@Entity
@Table(name = "ORDER_ITEM")
public class OrderItem {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ITEM_ID")
    private Long id;

    @Column(name = "ITEM_ID")
    private Long itemId;

    @Column(name = "ORDER_ID")
    private int orderId;

    private int orderPrice;
    private int count;
}
