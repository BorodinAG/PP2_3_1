package sof.dao;

import org.springframework.stereotype.Repository;
import sof.model.User;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserDao {


    @PersistenceContext
    private EntityManager entityManager;

    public UserDao() {

    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public User show(Long id) {
        TypedQuery<User> query = entityManager.createQuery("select u From User u where u.id=:id", User.class);
        query.setParameter("id", id);
        return query.getResultList().stream().findAny().orElse(null);

    }

    @Transactional
    public void update(User updatedUser) {
        entityManager.merge(updatedUser);
    }

    @Transactional
    public void delete(Long id) {
        entityManager.remove(show(id));
    }

    @Transactional
    public List<User> index() {
        TypedQuery<User> q =
                entityManager.createQuery("select u from User u", User.class);
        return q.getResultList();
    }


}
