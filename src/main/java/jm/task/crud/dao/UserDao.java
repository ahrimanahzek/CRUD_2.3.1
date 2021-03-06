package jm.task.crud.dao;

import jm.task.crud.model.User;

import java.util.List;

public interface UserDao {
    void createUsersTable();

    void dropUsersTable();

    void addUser(String name, String lastName, byte age);

    void saveUser(User user);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();

    User show(long id);

}
