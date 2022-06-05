package ghtk.spring.hw2.controller;

import ghtk.spring.hw2.entity.User;
import ghtk.spring.hw2.service.userService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:9000")
@RestController
@RequestMapping("/user")
public class userController {

    // annotation
    @Autowired
    private userService userService;

    // save operation

    @PostMapping("")
    public ResponseEntity<String> addUser(@RequestBody User user){
        String mess = userService.addUser(user);
        return new ResponseEntity<>(mess, HttpStatus.CREATED);
    }

    // read operation
    @GetMapping("")
    public ResponseEntity<List<User>> getAll(){
        List<User> users = userService.getAll();
        return new  ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> find(@PathVariable Integer id){
        return new ResponseEntity<>(userService.findById(id), HttpStatus.OK);
    }

    // update operation

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @RequestBody User user){
        String mess = userService.updateUser(id,user);
        return new ResponseEntity<>(mess, HttpStatus.OK);
    }

    // delete operation

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        String mess = userService.deleteUser(id);
        return new ResponseEntity<>(mess, HttpStatus.OK);
    }
}
