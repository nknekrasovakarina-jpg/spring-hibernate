package hiber.service;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImp implements UserService {

   private final SessionFactory sessionFactory;

   public UserServiceImp(SessionFactory sessionFactory) {
      this.sessionFactory = sessionFactory;
   }

   @Override
   @Transactional
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @Transactional
   public List<User> listUsers() {
      return sessionFactory.getCurrentSession().createQuery("from User", User.class).list();
   }

   @Override
   @Transactional
   public User getUserByCar(String model, int series) {
      String hql = "from User u where u.car.model = :model and u.car.series = :series";
      Query<User> query = sessionFactory.getCurrentSession()
              .createQuery(hql, User.class)
              .setParameter("model", model)
              .setParameter("series", series);
      return query.uniqueResult();
   }
}