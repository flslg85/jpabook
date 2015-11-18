package ch06;

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
            save(em);  //비즈니스 로직
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    public static void save(EntityManager em) {
        Member member = createMember();
        Delivery delivery = createDelivery();
        Order order = createOrder(member, delivery);
        Item item = createItem();
        OrderItem orderItem = createOrderItem(order, item);
        Category category = createCategory();
        CategoryItem categoryItem = createCategoryItem(item, category);

        em.persist(member);
        em.persist(delivery);
        em.persist(order);
        em.persist(item);
        em.persist(orderItem);
        em.persist(category);
        em.persist(categoryItem);
    }

    private static Delivery createDelivery() {
        Delivery delivery = new Delivery();
        delivery.setCity("평촌");
        return delivery;
    }

    private static CategoryItem createCategoryItem(Item item, Category category) {
        CategoryItem categoryItem = new CategoryItem();
        categoryItem.setCategory(category);
        categoryItem.setItem(item);
        return categoryItem;
    }

    private static Category createCategory() {
        Category category = new Category();
        category.setName("뷰티");
        return category;
    }

    private static Item createItem() {
        Item item = new Item();
        item.setName("sk II");
        item.setPrice(1212);
        item.setStockQuantity(111);
        return item;
    }

    private static OrderItem createOrderItem(Order order, Item item) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setItem(item);
        return orderItem;
    }

    private static Order createOrder(Member member, Delivery delivery) {
        Order order = new Order();
        order.setMember(member);
        order.setDelivery(delivery);
        order.setOrderDate(new Date(67678687));
        order.setStatus(OrderStatus.ORDER);
        return order;
    }

    private static Member createMember() {
        Member member = new Member();
        member.setName("발칙한것");
        return member;
    }

}