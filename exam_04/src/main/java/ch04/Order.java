package ch04;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by we on 2015. 11. 17..
 */
@Entity
@Table(name = "ORDERS")
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "ORDER_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<OrderItem>();

    @Temporal(TemporalType.TIMESTAMP)
    private Date orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    /* method */

    public void setMember(Member member) {
        if (member != null) {
            this.member.getOrders().remove(this);
        }
        this.member = member;   // order -> member
        this.member.getOrders().add(this);  // member -> order
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
}
