package jm.task.crud.model;

import jm.task.crud.dao.UserDao;
import jm.task.crud.dao.UserDaoHibernateImpl;

public class Test {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoHibernateImpl();

        //userDao.saveUser("Tigrov", "Tigr", (byte) 99);

        for (User user : userDao.getAllUsers()) {
            System.out.println(user.getName());
        }
    }
}
