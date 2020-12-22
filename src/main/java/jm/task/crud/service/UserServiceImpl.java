package jm.task.crud.service;
import jm.task.crud.dao.*;

import jm.task.crud.model.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoHibernateImpl();
    }

    public void createUsersTable() {

        userDao.createUsersTable();

    }

    public void dropUsersTable() {

        userDao.dropUsersTable();

    }

    public void saveUser(User user) {

        userDao.saveUser(user);

    }

    public void addUser(String name, String lastName, byte age) {

        userDao.addUser(name, lastName, age);

    }

    public void removeUserById(long id) {
       userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {

        return userDao.getAllUsers();

    }

    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }

    @Override
    public User show(long id) {
        return userDao.show(id);
    }
}
