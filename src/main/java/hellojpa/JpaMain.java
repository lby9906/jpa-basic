//package hellojpa;
//
//import jakarta.persistence.*;
//import org.hibernate.Hibernate;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//public class JpaMain {
//
//    public static void main(String[] args) {
//
//        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");
//        EntityManager em = emf.createEntityManager();
//
//        EntityTransaction tx = em.getTransaction();
//        tx.begin();
//
//        try {
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setHomeAddress(new Address("homeCity", "street", "10000"));
//
//            member.getFavoriteFoods().add("치킨");
//            member.getFavoriteFoods().add("치킨");
//            member.getFavoriteFoods().add("피자");
//
//            member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
//            member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));
//
//            em.persist(member);
//
//            em.flush();
//            em.clear();
//
//            System.out.println("=================START==================");
//            Member findMember = em.find(Member.class, member.getId());
//
//            Address a = findMember.getHomeAddress();
//            findMember.setHomeAddress(new Address("newCity", a.getStreet(), a.getZipCode()));
//
//            findMember.getFavoriteFoods().remove("치킨");
//            findMember.getFavoriteFoods().add("한식");
//
////            findMember.getAddressHistory().remove(new Address("old1", "street", "10000"));
////            findMember.getAddressHistory().add(new Address("newCity1", "street", "10000"));
//
//            tx.commit();
//        } catch (Exception e){
//            tx.rollback();
//            e.printStackTrace();
//        }finally {
//            em.close();
//        }
//        emf.close();
//    }
//
//}
