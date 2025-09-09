package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Transactional
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Transactional
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession()
              .createQuery("from User", User.class)
              .getResultList();
   }

   @Transactional
   public User getUserByCar(String model, int series) {
      return sessionFactory.getCurrentSession()
              .createQuery("FROM User u WHERE u.car.model = :model AND u.car.series = :series", User.class)
              .setParameter("model", model)
              .setParameter("series", series)
              .uniqueResult();
   }
}