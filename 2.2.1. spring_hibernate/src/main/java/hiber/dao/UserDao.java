package hiber.dao;

import hiber.model.User;
import org.springframework.stereotype.Repository;
import javax.persistence.*;
import java.util.List;

@Repository
public class UserDao {

   @PersistenceContext
   private EntityManager em;

   public void save(User user) {
      em.persist(user);
   }

   public List<User> listUsers() {
      return em.createQuery("from User", User.class).getResultList();
   }

   public User getUserByCar(String model, int series) {
      TypedQuery<User> query = em.createQuery(
              "select u from User u where u.car.model = :model and u.car.series = :series", User.class);
      query.setParameter("model", model);
      query.setParameter("series", series);
      return query.getResultStream().findFirst().orElse(null);
   }
}