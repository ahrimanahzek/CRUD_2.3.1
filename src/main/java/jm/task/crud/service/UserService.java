package jm.task.crud.service;

import jm.task.crud.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void createUsersTable();

    void dropUsersTable();

    void addUser(String name, String lastName, byte age);

    void saveUser(User user);

    void removeUserById(long id);

    List<User> getAllUsers();

    void cleanUsersTable();

    User show(long id);
}
