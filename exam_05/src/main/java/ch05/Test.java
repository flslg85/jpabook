package ch05;

import javax.persistence.*;
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
            testSave(em);  //비즈니스 로직
            testGetTeam1(em);
            testGetTeam2(em);
            tx.commit();//트랜잭션 커밋

        } catch (Exception e) {
            e.printStackTrace();
            tx.rollback(); //트랜잭션 롤백
        } finally {
            em.close(); //엔티티 매니저 종료
        }

        emf.close(); //엔티티 매니저 팩토리 종료
    }

    /**
     * 저장
     */
    public static void testSave(EntityManager em) {
        Team team1 = new Team("team1", "팀1");
        em.persist(team1);
        
        Member member1 = new Member("member1", "회원1");
        member1.setTeam(team1);
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        em.persist(member2);
    }

    /**
     * 조회
     */
    // 객체 그래프 탐색( 객체 연관관계를 사용한 조회 )
    public static void testGetTeam1(EntityManager em) {
        Member member = em.find(Member.class, "member1");
        Team team = member.getTeam();
        System.out.println("team : " + team.getName());
    }

    // 객체 지향 쿼리 사용
    public static void testGetTeam2(EntityManager em) {
        String jpql = "select m from Member m join m.team t where t.name=:teamName";
        List<Member> memberList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();

        for (Member member : memberList) {
            System.out.println("[query] member.name : " + member.getName());
        }
    }

}