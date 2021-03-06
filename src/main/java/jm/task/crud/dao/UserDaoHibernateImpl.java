package jm.task.crud.dao;

import jm.task.crud.util.Util;
import jm.task.crud.model.*;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Query;

@Repository
public class UserDaoHibernateImpl implements UserDao {

    String sqlQueryCreateTable = "CREATE TABLE IF NOT EXISTS `testdb`.`users` (\n" +
            "  `id` BIGINT(1) NOT NULL AUTO_INCREMENT,\n" +
            "  `name` VARCHAR(45) NULL,\n" +
            "  `lastName` VARCHAR(45) NULL,\n" +
            "  `age` TINYINT(1) NULL,\n" +
            "  PRIMARY KEY (`id`))\n" +
            "ENGINE = InnoDB\n" +
            "DEFAULT CHARACTER SET = utf8;\n";

    String sqlQueryDeleteTable = "DROP TABLE IF NOT EXISTS `users`";

    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {

        Util util = new Util();

        Connection connection = util.getConnection();

        try {

            connection.setAutoCommit(false);

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE 'users'");

            boolean tableExist = false;

            while (resultSet.next()) {
                tableExist = true;
            }

            if (!tableExist) {
                statement.execute(sqlQueryCreateTable);
                System.out.println("В базу данных добавлена таблица users");
                connection.commit();
            } else {
                System.out.println("Таблица users уже есть в базе данных!");
            }

        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
/*            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }*/
        }
    }

    @Override
    public void dropUsersTable() {
        Util util = new Util();

        Connection connection = util.getConnection();

        try {
            connection.setAutoCommit(false);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SHOW TABLES LIKE 'users'");

            boolean tableExist = false;

            while (resultSet.next()) {
                tableExist = true;
            }

            if (tableExist) {
                statement.execute(sqlQueryDeleteTable);
                System.out.println("Из базы данных удалена таблица users");
                connection.commit();
            } else {
                System.out.println("Невозможно удалить таблицу users! Ее нет в базе данных");
            }

        } catch (SQLException throwables) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            throwables.printStackTrace();
        } finally {
/*            try {
                connection.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }*/
        }
    }

    @Override
    public void saveUser(User user) {
        Transaction transaction = null;

        //Session session = Util.getSessionFactory().openSession();
        EntityManager em = Util.getSessionFactory();
        // auto close session object
        try {

            // start the transaction
            em.getTransaction().begin();

            // save student object
            if(user.getId() > 0) {
                em.persist(user);
            } else {
                em.persist(user);
            }

            // commit transction
            em.getTransaction().commit();
           // em.close();
        } catch (Exception e) {
            if (transaction != null) {
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void addUser(String name, String lastName, byte age) {

        Transaction transaction = null;

        //Session session = Util.getSessionFactory().openSession();
        EntityManager em = Util.getSessionFactory();

        User user = new User();
        user.setName(name);
        user.setLastName(lastName);
        user.setAge(age);

        // auto close session object
        try {

            em.getTransaction().begin();
            em.persist(user);
            em.getTransaction().commit();

            // start the transaction
            //transaction = session.beginTransaction();

            // save student object
            //session.save(user);
/*            String hql = "insert into User (name, lastName, age) " +
                    "select 'oleg', 'Олег' from User";
            int rows = session.createQuery (hql).executeUpdate();
            System.out.println("rows : " + rows);*/


            // commit transction
            //transaction.commit();
           // em.close();
        } catch (Exception e) {
            if (transaction != null) {
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;

        //Session session = Util.getSessionFactory().openSession();
        EntityManager em = Util.getSessionFactory();

/*        User user = new User();
        user.setId(id);*/

        User user = show(id);

        // auto close session object
        try {

            // start the transaction
             em.getTransaction().begin();

            // save student object
            //session.delete(user);
            ///session.createQuery("DELETE User WHERE login = :lg");
            em.remove(user);
/*            String hql = "DELETE User WHERE id = :lg";
            Query query = session.createQuery(hql);
            query.setParameter("lg", id);
            int rows = query.executeUpdate();*/

            // commit transction
            em.getTransaction().commit();
        } catch (Exception e) {
            if (transaction != null) {
                em.getTransaction().rollback();
            }
        }
    }

    @Override
    public List<User> getAllUsers() {

        //Session session = Util.getSessionFactory().openSession();
        EntityManager session = Util.getSessionFactory();

        Query q = session.createQuery("From User");

        List<User> resultList = q.getResultList();//.list();

        return resultList;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;

        //Session session = Util.getSessionFactory().openSession();
        EntityManager session = Util.getSessionFactory();

        // auto close session object
        try {

            // start the transaction
            //transaction = session..beginTransaction();

            // save student object
            //session.delete(user);
            session.createQuery("delete from User");
            //session.

            // commit transction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        }
    }

    @Override
    public User show(long id) {
        List<User> users = getAllUsers();
        return users.stream().filter(user -> user.getId() == id).findAny().orElse(null);
    }
}
