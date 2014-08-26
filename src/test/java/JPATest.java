import entity.User;
import entity.User2;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JPATest {

    @Test
    public void testUpdate() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu-h2");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        // insert
        transaction.begin();

        User user = new User("eiryu");
        em.persist(user);

        transaction.commit();

        // update
        transaction.begin();

        User updateUser = em.createQuery("select u from User u where u.id = 1", User.class).getSingleResult();
        System.out.println("id: " + updateUser.getId());
        System.out.println("name: " + updateUser.getName());
        System.out.println("version: " + updateUser.getVersion());

        updateUser.setName("updated");

        transaction.commit();

        // check
        User result = em.createQuery("select u from User u where u.id = 1", User.class).getSingleResult();
        System.out.println("id: " + result.getId());
        System.out.println("name: " + result.getName());
        System.out.println("version: " + result.getVersion());

    }

    @Test
    public void testUpdateAvoidOptimisticLocking() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("pu-h2");
        EntityManager em = factory.createEntityManager();
        EntityTransaction transaction = em.getTransaction();

        // insert
        transaction.begin();

        User2 user = new User2("eiryu");
        em.persist(user);

        transaction.commit();

        // update
        transaction.begin();

        User2 updateUser = em.createQuery("select u from User2 u where u.id = 1", User2.class).getSingleResult();
        System.out.println("id: " + updateUser.getId());
        System.out.println("name: " + updateUser.getName());
        System.out.println("version: " + updateUser.getVersion());

        updateUser.setName("updated");

        transaction.commit();

        // check
        User2 result = em.createQuery("select u from User2 u where u.id = 1", User2.class).getSingleResult();
        System.out.println("id: " + result.getId());
        System.out.println("name: " + result.getName());
        System.out.println("version: " + result.getVersion());

    }

}
