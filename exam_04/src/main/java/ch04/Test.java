package ch04;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;


/**
 * Created by we on 2015. 11. 16..
 */
public class Test {
    public static void main(String[] args) {
        //엔티티 매니저 팩토리 생성
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpabook");
        EntityManager em = emf.createEntityManager(); //엔티티 매니저 생성
        EntityTransaction tx = em.getTransaction(); //트랜잭션 기능 획득

        try {
            tx.begin(); //트랜잭션 시작
            test(em);  //비즈니스 로직
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void test(EntityManager em) {
        Member member = new Member();
        member.setName("name1");
        member.setCity("city1");
        member.setStreet("street1");
        member.setZipcode("zipcode1");


        Order order = new Order();
        order.setMemberId(member.getId());
        order.setOrderDate(new Date(67678687));
        order.setStatus(OrderStatus.CANCEL);

        OrderItem orderItem = new OrderItem();
        orderItem.setCount(1);
        orderItem.setItemId(new Long(5555));
        orderItem.setOrderId(2323);
        orderItem.setOrderPrice(1111);

        Item item = new Item();
        item.setName("name1");
        item.setPrice(1212);
        item.setStockQuantity(111);


        em.persist(member);
        em.persist(order);
        em.persist(orderItem);
        em.persist(item);
    }

}