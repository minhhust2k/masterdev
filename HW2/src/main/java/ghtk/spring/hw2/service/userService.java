package ghtk.spring.hw2.service;

import ghtk.spring.hw2.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface userService extends Service {
    List<User> getAll();
    User findById(int id);
    String addUser(User u);
    String deleteUser(int id);
    String updateUser(int id, User user);
}
