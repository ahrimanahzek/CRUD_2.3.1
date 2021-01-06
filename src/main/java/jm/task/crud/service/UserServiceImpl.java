package jm.task.crud.service;
import jm.task.crud.dao.*;

import jm.task.crud.model.*;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    UserDao userDao;

    public UserServiceImpl() {
        userDao = new UserDaoHibernateImpl();
    }

    @Transactional
    public void createUsersTable() {

        userDao.createUsersTable();

    }

    @Transactional
    public void dropUsersTable() {

        userDao.dropUsersTable();

    }

    @Transactional
    public void saveUser(User user) {

        userDao.saveUser(user);

    }

    @Transactional
    public void addUser(String name, String lastName, byte age) {

        userDao.addUser(name, lastName, age);

    }

    @Transactional
    public void removeUserById(long id) {
       userDao.removeUserById(id);
    }

    @Transactional
    public List<User> getAllUsers() {

        return userDao.getAllUsers();

    }

    @Transactional
    public void cleanUsersTable() {
        userDao.cleanUsersTable();
    }

    @Override
    @Transactional
    public User show(long id) {
        return userDao.show(id);
    }
}
