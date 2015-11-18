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
//            testGetTeam1(em);
//            testGetTeam2(em);
//            testUpdate(em);
//            deleteRelation(em);
//            deleteEntity(em);
            biDirection(em);
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
        team1.getMembers().add(member1);    // 객체의 주인이 아니기 때문에 저장시 사용되지 않음
        em.persist(member1);

        Member member2 = new Member("member2", "회원2");
        member2.setTeam(team1);
        team1.getMembers().add(member2);
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
        String jpql = "select m from Member m join m.team t where t.name=:teamName";    // : 로 시작하는 것은 파라미터를 바인딩 받는 문법임
        List<Member> memberList = em.createQuery(jpql, Member.class)
                .setParameter("teamName", "팀1")
                .getResultList();

        for (Member member : memberList) {
            System.out.println("[query] member.name : " + member.getName());
        }
    }

    /**
     * 수정
     */
    public static void testUpdate(EntityManager em) {
        Team team2= new Team("team2", "팀2");
        em.persist(team2);

        Member member = em.find(Member.class, "member1");
        member.setTeam(team2);
    }

    /**
     * 삭제
     */
    private static void deleteRelation(EntityManager em) {
        Member member1 = em.find(Member.class, "member1");
        member1.setTeam(null);  // 연관관계 제거
    }

    /**
     * 연관된 엔티티 삭제
     *  - 기존에 있던 연관관계를 먼저 제거하고 삭제해야 함
     *  - 그렇지 않으면 외래 키 제약조건으로 인해, 데이터베이스에서 오류가 발생
     */
    private static void deleteEntity(EntityManager em) {
        Member member1 = em.find(Member.class, "member1");
        Member member2 = em.find(Member.class, "member2");

        Team team = em.find(Team.class, "team1");

        member1.setTeam(null);
        member2.setTeam(null);

        em.remove(team);
    }

    /**
     * 일대다 방향으로 객체 그래프 탐색
     */
    private static void biDirection(EntityManager em) {
        Team team = em.find(Team.class, "team1");
        List<Member> members = team.getMembers();

        for (Member member : members) {
            System.out.println("member.name = " + member.getName());
        }
    }
}