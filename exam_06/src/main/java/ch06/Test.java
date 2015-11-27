package ch06;

import com.mysema.query.jpa.impl.JPAQuery;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.Date;
import java.util.List;


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
            jpqlTest(em);
            criteriaTest(em);
            queryDSL(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    private static void criteriaTest(EntityManager em) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Member> query = cb.createQuery(Member.class);

        // 조회를 시작할 클래스
        Root<Member> m = query.from(Member.class);

        CriteriaQuery<Member> cq = query.select(m).where(cb.equal(m.get("name"), "발칙한것"));
        List<Member> resultList = em.createQuery(cq).getResultList();

        for (Member member : resultList) {
            System.out.println("criteria member.name : " + member.getName());
        }
    }

    private static void queryDSL(EntityManager em) {
        JPAQuery query = new JPAQuery(em);
        QMember qMember = new QMember("m"); // 생성되는 JPQL 의 별칭이 m
        List<Member> members = query
                .from(qMember)
                .where(qMember.name.eq("발칙한것"))
                .orderBy(qMember.name.desc())
                .list(qMember);

        for (Member member : members) {
            System.out.println("queryDSL member.name : " + member.getName());
        }
    }

    private static void jpqlTest(EntityManager em) {
        String jpql = "select m from Member as m where m.name = '발칙한것'";
        List<Member> resultList = em.createQuery(jpql, Member.class)
                .getResultList();

        for (Member member : resultList) {
            System.out.println("member.name : " + member.getName());
        }
    }

    public static void save(EntityManager em) {
        Member member = createMember();

        Delivery delivery = createDelivery();

        Order order = createOrder();
        order.setMember(member);
        order.setDelivery(delivery);

        Item item = createItem();

        OrderItem orderItem = createOrderItem();
        orderItem.setItem(item);
        orderItem.setOrder(order);

        Category category = createCategory();

        CategoryItem categoryItem = createCategoryItem();
        categoryItem.setItem(item);
        categoryItem.setCategory(category);

        em.persist(item);
        em.persist(category);
        em.persist(member);
    }

    private static Delivery createDelivery() {
        Delivery delivery = new Delivery();
        delivery.setCity("평촌");
        return delivery;
    }

    private static CategoryItem createCategoryItem() {
        CategoryItem categoryItem = new CategoryItem();
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

    private static OrderItem createOrderItem() {
        OrderItem orderItem = new OrderItem();
        return orderItem;
    }

    private static Order createOrder() {
        Order order = new Order();
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