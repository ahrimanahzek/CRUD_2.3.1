package jm.task.crud.controllers;

import jm.task.crud.dao.UserDao;
import jm.task.crud.dao.UserDaoHibernateImpl;
import jm.task.crud.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserDao userDao;

    @Autowired
    public UserController(UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public String index(Model model) {
        // получим всех пользователей и выведем их
        List<User> users = userDao.getAllUsers();
        model.addAttribute("users", users);
        return "user/index";
    }
}
