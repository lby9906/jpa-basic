package jpql;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Teams teams = new Teams();
            teams.setName("teamA");
            em.persist(teams);

            for (int i=0; i<100; i++){
                MemberList memberList = new MemberList();
                memberList.setUsername("member1");
                memberList.setAge(10);

                memberList.setTeams(teams);

                em.persist(memberList);
            }

            em.flush();
            em.clear();

            String query =
                    "select " +
                            "case when m.age <= 10 then '학생요금'" +
                            "     when m.age >= 60 then '경로요금'" +
                            "     else '일반요금'" +
                            "end " +
                    "from MemberList m";

            List<String> result = em.createQuery(query, String.class)
                    .getResultList();

            for (String s : result) {
                System.out.println("s = " + s);
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
