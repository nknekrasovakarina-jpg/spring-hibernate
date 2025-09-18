package hiber.dao;

import hiber.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(User user) {
        em.persist(user);
    }

    @Override
    public List<User> listUsers() {
        return em.createQuery("from User", User.class).getResultList();
    }

    @Override
    public User getUserByCar(String model, int series) {
        TypedQuery<User> query = em.createQuery(
                "select u from User u where u.car.model = :model and u.car.series = :series", User.class);
        query.setParameter("model", model);
        query.setParameter("series", series);
        return query.getResultStream().findFirst().orElse(null);
    }
}