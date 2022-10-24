package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;
import peaksoft.model.User;
import peaksoft.util.Util;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {
        System.out.println("Auto creation");
    }

    @Override
    public void dropUsersTable() {
        System.out.println("Auto drop after close");
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
            System.out.println("User with name: " + name + " successfylly created!");
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        }
    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            User student = session.get(User.class, id);
            System.out.println(student);
            session.delete(student);
            session.getTransaction().commit();
            System.out.println("User with id: " + id + " successfully deleted!");
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<User> student = session.createQuery("from User").getResultList();
            session.getTransaction().commit();

            System.out.println(student.size() + " user(s) have(has) been found!");

            return student;
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().openSession()) {
            session.beginTransaction();
            Query query = session.createQuery("delete from User");
            query.executeUpdate();
            session.getTransaction().commit();

            System.out.println("All users successfully deleted!");
        } catch (HibernateException h) {
            System.out.println(h.getMessage());
        }
    }
}
