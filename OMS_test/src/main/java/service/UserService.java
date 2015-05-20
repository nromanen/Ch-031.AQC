package service;

import entity.UserModel;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * Created by diagon on 08.05.15.
 */
public class UserService {

    private EntityManager em = Persistence.createEntityManagerFactory("DBUnitEx").createEntityManager();

    public void save(UserModel Users){
        em.getTransaction().begin();
        em.persist(Users);
        em.getTransaction().commit();
    }

    public void delete(UserModel Users) {
        em.getTransaction().begin();
        em.remove(Users);
        em.getTransaction().commit();
    }

    public UserModel get(int id) {
        return em.find(UserModel.class, id);
    }

    public void update(UserModel Users) {
        em.getTransaction().begin();
        em.merge(Users);
        em.getTransaction().commit();
    }

}
