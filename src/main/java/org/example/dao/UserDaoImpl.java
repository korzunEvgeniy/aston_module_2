package org.example.dao;

import org.example.util.HibernateUtil;
import org.example.entity.User;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;

public class UserDaoImpl implements UserDao{
    private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);

    @Override
    public void save(User user) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.persist(user);
            transaction.commit();
            logger.info("User saved successfully: {}", user.getName());
        }  catch (Exception e) {
            logger.error("Error saving user", e);
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public Optional<User> findById(int id) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            User user = (User) session.get(User.class, id);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            logger.error("Error finding user by id: {}", id, e);
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            User user = (User) session.get(User.class, email);
            return Optional.ofNullable(user);
        } catch (Exception e) {
            logger.error("Error finding user by email: {}", email, e);
            return Optional.empty();
        }
    }

    @Override
    public List<User> findAll() {
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            return session.createQuery("From User").list();
        } catch (Exception e) {
            logger.error("No anything user");
            return List.of();
        }
    }

    @Override
    public void update(User user) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.merge(user);
            transaction.commit();
            logger.info("User updated successfully: {}", user.getName());
        } catch (Exception e) {
            logger.error("Error updating user", e);
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public void delete(User user) {
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.delete(user);
            transaction.commit();
            logger.info("User deleted successfully: {}", user.getName());
        } catch (Exception e) {
            logger.error("Error deleting user", e);
            if(transaction != null) {
                transaction.rollback();
            }
        }
    }
}
