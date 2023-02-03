package hiber.dao;

import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@SuppressWarnings("ALL")
@Repository
public class UserDaoImp implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> listUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override

    public User getUserByModelAndSeries(String model, int series) {
        User user = null;
        try {
            user = (User) sessionFactory
                    .getCurrentSession()
                    .createQuery("from User user where user.car.model=:p1 AND user.car.series=:p2")
                    .setParameter("p1", model)
                    .setParameter("p2", series)
                    .getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }


}
