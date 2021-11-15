package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class helloJPa {
    public static void main(String[] args) {
        /**
         * 1. HelloJPA - 애플리케이션 개발 : JPA의 동작원리를 알기 위해 Spring Data JPA를 사용하지 않고
         *                                  순수 JPA를 사용하여 어떤식으로 JPA가 작동하는 지 코드를 통해 알아봄.
         *
         * JPA를 실행하게되면 EntityManagerFactory 객체를 생성하고, 그 객체에서 EntityManager 객체를 만들면서
         * JPA가 작동하게 된다. 또한 JPA는 트랜잭션 안에서 작동되기 때문에 트랜잭션이 없으면 예외를 발생시키게 된다.
         * 트랜잭션은 뒤 파트에서 설명될 예정이다.
         *
         * Persistence.createEntityManagerFactory를 통해 EntityManagerFactory 객체를 생성하고, 거기서 EntityManager 객체를
         * 생성, Transaction을 만든 다음에 Member 테이블에 이름을 넣어서 저장시켜보았다.
         *
         * persist 메서드가 중요한데, 생성한 객체에 담긴 데이터를 테이블에 저장하는 메서드이다. member 객체에 담긴 id와 name이
         * H2 DB에 있는 Member 테이블에 저장되게되고, 커밋을 진행하게된다. 만약 예외가 발생할 경우에는 rollback으로 롤백시킨다.
         *
         * 만약 코드를 다 실행하고 난 다음 EntityManeger와 EntityManagerFactory를 close() 시켜주지 않으면 리소스에 남아있기 때문에
         * 낭비가 된다. 따라서 close를 시켜주어야 한다.
         *
         * 여기에서는 find, remove, persist, 객체(테이블)의 setXXX 메서드를 통해 추가, 수정, 삭제를 만드는 코드를 작성해보았다.
         */
        // EntityManagerFactory 객체를 호출
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        // EntityManagerFactory에서 EntityManager 객체를 하나 만듦
        EntityManager entityManager = emf.createEntityManager();

        // JPA는 트랜잭션 내에서 작동된다. 따라서 트랜잭션이 없으면 예외륿 발생시킨다(최신버전은 따로 설정을 하는 것 같다.)
        // entityManager 객체의 transaction 객체를 가져와서 객체 생성
        EntityTransaction tx = entityManager.getTransaction();

        tx.begin(); // transaction 시작

        try {

            Member2 member2 = new Member2();
            member2.setUsername("memberA");
            member2.setAge(25);

            entityManager.persist(member2);

            Member2 member22 = new Member2();
            member22.setUsername("memberB");
            member22.setAge(27);

            entityManager.persist(member22);

            // Member 객체에 Id와 이름을 넣고 persist로 저장
            // member.setId(2L);
            // member.setName("helloB"); // 비영속(객체를 생성하고 세팅까지만 완료한 상태)
            // entityManager.persist(member); // 영속(생성한 member 객체를 em에 넣어서 em 안에있는 영속성 context로 관리되는 상태


            // Member 테이블에서 id가 1인 데이터를 가지고 오는 메서드 : find()
            /*Member findMember = entityManager.find(Member.class, 1L);

            System.out.println("Member.getId = " + findMember.getId());
            System.out.println("findMember.getName() = " + findMember.getName())*/


            // find로 가져온 멤버 객체를 삭제하는 메서드 : remove();
            //Member findMember = entityManager.find(Member.class, 1L);
            //entityManager.remove(findMember);

            // JPA로 테이블 내의 컬럼 수정하는 방법,
            // 1. find로 수정할 테이블의 데이터를 DTO 객체를 통해 가져온다.
            // 2. 해당 DTO 객체의 setXXX 메서드를 사용하면 자동으로 바뀐다.
            // Member findMember = entityManager.find(Member.class, 2L);
            // findMember.setName("helloJPA");

            // JSQL 맛보기 : JSQL은 테이블이 기준이 아니라 Entity를 기준으로 쿼리문을 작성한다. 아래의 코드를 보면
            // Member 테이블이 아닌 Entity로 지정한 Member 클래스를 기준으로 컬렉션을 가져오는 것을 볼 수 있다.
            // 이럴 경우 DB가 달라지더라도 DB에 종속되지않고 객체지향적으로 쿼리문을 날릴 수 있다는 장점이 있다.
            // 참고로 setFirstResult, setMaxResults는 1부터 10번 행 까지의 데이터를 가져오라는 메서드이다.
//            List<Member> resultList = entityManager.createQuery("select m from Member as m", Member.class)
//                    .setFirstResult(1).setMaxResults(10).getResultList();

            tx.commit();

        } catch(Exception e) {
            tx.rollback();
        } finally {
            entityManager.close();
        }

        emf.close();
    }
}
