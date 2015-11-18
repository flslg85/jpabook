package ch06;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by we on 2015. 11. 17..
 */
@Entity
@Table(name = "ORDERS", uniqueConstraints = {
        @UniqueConstraint(
                name = "DELIVERY_ID_UNIQUE",
                columnNames = {
                        "DELIVERY_ID"
                }
        )
})

public class Order {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @OneToOne
    @JoinColumn(name = "DELIVERY_ID", nullable = false)
    private Delivery delivery;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /* method */

    public void setMember(Member member) {
        this.member = member;   // order -> member

        if (!this.member.getOrders().contains(member)) {
            this.member.getOrders().add(this);
        }
    }

    public void addOrderItem(OrderItem orderItem) {
        this.orderItems.add(orderItem); // order -> orderItem
        orderItem.setOrder(this);   // orderItem -> order
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
