package jpql;

import hellojpa.Team;
import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Teams teamsA = new Teams();
            teamsA.setName("팀A");
            em.persist(teamsA);

            Teams teamsB = new Teams();
            teamsB.setName("팀B");
            em.persist(teamsB);

            MemberList member1 = new MemberList();
            member1.setUsername("회원1");
            member1.setTeams(teamsA);
            em.persist(member1);

            MemberList member2 = new MemberList();
            member2.setUsername("회원2");
            member2.setTeams(teamsA);
            em.persist(member2);

            MemberList member3 = new MemberList();
            member3.setUsername("회원3");
            member3.setTeams(teamsB);
            em.persist(member3);

            em.flush();
            em.clear();

            String query = "select t from Teams t join fetch t.memberLists";

            List<Teams> result = em.createQuery(query, Teams.class)
                    .setFirstResult(0)
                    .setMaxResults(2)
                    .getResultList();

            for (Teams teams : result) {
                System.out.println("member = " + teams.getName() + "|members= " + teams.getMemberLists().size());
                for( MemberList memberList : teams.getMemberLists()) {
                    System.out.println("memberList = " + memberList);
                }
            }

            tx.commit();
        } catch (Exception e){
            tx.rollback();
            e.printStackTrace();
        }finally {
            em.close();
        }
        emf.close();
    }

}
