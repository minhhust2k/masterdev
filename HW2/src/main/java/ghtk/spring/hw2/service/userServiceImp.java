package ghtk.spring.hw2.service;

import ghtk.spring.hw2.entity.User;
import ghtk.spring.hw2.exception.NotFoundException;
import ghtk.spring.hw2.repository.userRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class userServiceImp implements userService {

    @Autowired
    private userRepository userRepos;

    private final PasswordEncoder passwordEncoder;
    @Override
    public List<User> getAll() {
        return userRepos.findAll();
    }

    @Override
    public User findById(int id) throws NotFoundException {
        Optional<User> user = userRepos.findById(id);
        if (user.isPresent()){
            return user.get();
        }
        else {
            throw new NotFoundException("User not found !");
        }
    }

    @Override
    public String addUser(User user) {
        if (Objects.equals(user.getPassword(), "") || Objects.equals(user.getEmail(), "")){
            return "Fail!";
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            this.userRepos.save(user);
            return "Successful!";
        }
    }
    @Override
    public String deleteUser(int id) {
        Optional<User> u = userRepos.findById(id);
        if (u.isPresent()){
            userRepos.deleteById(id);
            return "Successful!";
        }
        else {
            return "Index " + id + " doesn't exist !";
        }
    }

    @Override
    public String updateUser(int id, User user) {
        User u = this.findById(id);
        u.setEmail(user.getEmail());
        u.setPassword(passwordEncoder.encode(user.getPassword()));
        this.userRepos.save(u);
        return "Successful !";
    }

    @Override
    public String value() {
        return null;
    }

    @Override
    public Class<? extends Annotation> annotationType() {
        return null;
    }
}
