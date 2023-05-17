package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;



public class UserDaoHibernateImpl implements UserDao {


    private static final SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
//        String sql = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age INT NOT NULL, PRIMARY KEY (id));";
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            String sql = "CREATE TABLE IF NOT EXISTS users (id INT NOT NULL AUTO_INCREMENT, name VARCHAR(45) NOT NULL, lastName VARCHAR(45) NOT NULL, age INT NOT NULL, PRIMARY KEY (id))";
            transaction = session.beginTransaction();
              session.createSQLQuery(sql).executeUpdate();

            transaction.commit();
            System.out.println("TABLE OK");
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("TABLE EXCEPTION");


        }



    }

    @Override
    public void dropUsersTable() {
//        String sql = "DROP TABLE IF EXISTS users";
        Transaction transaction = null;

        try (Session session = sessionFactory.openSession()) {
            String sql = "DROP TABLE IF EXISTS users";
            transaction = session.beginTransaction();
            session.createSQLQuery(sql).addEntity(User.class).executeUpdate();

            transaction.commit();
            System.out.println("DELETE OK");
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("DELETE EXCEPTION");
        }



    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
            Transaction transaction = null;


        try(Session session = sessionFactory.openSession()) {

            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.persist(user);
            transaction.commit();

            System.out.println("USER OK");
        }catch (HibernateException e) {
                if(transaction != null) {
                    transaction.rollback();
                }
            e.printStackTrace();
            System.out.println("saveUser EXCEPTION");
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
//            session.beginTransaction();
            User user = session.get(User.class, id);
            session.remove(user);
            transaction.commit();

            System.out.println("REMOVE OK");
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("remove EXCEPTION");
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
//        String sql = "from User";
        Transaction transaction = null;

        try(Session session = sessionFactory.openSession()) {

            String sql = "select c from User c";
            transaction = session.beginTransaction();
            list = session.createQuery(sql, User.class).getResultList();

            transaction.commit();

            System.out.println("getAll OK");
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("getAll EXCEPTION");
        }

        return list;
    }

    @Override
    public void cleanUsersTable() {
//        String sql = "DELETE User";
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {

            String sql = "DELETE from User";
            transaction = session.beginTransaction();
            session.createQuery(sql).executeUpdate();
            transaction.commit();

            System.out.println("cleanUser OK");
        } catch (HibernateException e) {
            if(transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
            System.out.println("cleanUser EXCEPTION");
        }
    }
}
