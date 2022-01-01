package ola.ejb;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class UserService {

    @PersistenceContext(unitName = "default")
    private EntityManager em;

    public UserService() {
        // Java EE
    }


    public void save(String username, String firstName, String lastName) {

        User user = new User(username, firstName, lastName);
        em.persist(user);
    }

    public List getAll() {

        return em.createQuery("SELECT u FROM User u").getResultList();
    }
}
