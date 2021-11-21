package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class helloJpa_relationship {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();

        tx.begin(); // transaction 시작

        try {
            Team team = new Team();
            team.setName("TeamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setTeam(team); // member객체에 team 추가
            em.persist(member);

            // 양방향 연관관계에서는 양 객체 모두 데이터를 집어넣어주는 것이 좋다.
            // team.getMembers().add(member); // team 객체에 member 추가


//            1차캐시에서 가져오지말고 바로 DB에 쿼리문 날려서 가져오는걸 확인하고 싶을 때 flush() 사용
//            em.flush();
//            em.clear();

            /**
             * 연관관계를 사용하지 않을 경우에는 한 명의 멤버가 소속된 팀의 객체를 얻기 위해서
             * find(member_id)로 member 객체를 찾고, 거기서 getTeamId로 team_id를 찾은 다음에 find(team_id)를 통해
             * Team 객체를 찾아야하는 번거로운 절차를 수행해야한다. 연관관계를 사용하면 객체 자체를 필드에 놓을 수 있고,
             * @JoinColumn을 통해 Join하는 기준이 되는 컬럼을 JPA에서 자동으로 매핑하여 DB에 접근하기 때문에 이런 절차를
             * 수행할 필요가 없어진다.
             */
//            연관관계를 사용하지 않을 경우
//            Member findMember = em.find(Member.class, member.getId());
//            Long findTeamId = findMember.getTeamId();
//            Team findTeam = em.find(Team.class, findTeamId);



            em.flush();
            em.clear();



            Member findMember = em.find(Member.class, member.getId());
            Team findTeam = findMember.getTeam();

            List<Member> members = findMember.getTeam().getMembers();

            for(Member m : members) {
                System.out.println("m.getUsername() = " + m.getUsername());
            }

            System.out.println("findTeam = " + findTeam);
            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
    }
}
