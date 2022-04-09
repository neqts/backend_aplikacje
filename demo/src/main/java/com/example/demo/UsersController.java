package com.example.demo;

import com.example.demo.UserEntity;
import com.example.demo.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class UsersController {
    @Autowired
    private UsersService usersService;

    @RequestMapping("/users")
    public String users() {
        return "users";
    }

    @RequestMapping("/api/users")
    @ResponseBody
    public String getApiUsers() {
        return this.usersService.getUsers();
    }

    @RequestMapping("/api/users/{id}")
    @ResponseBody
    public String getApiUser(
            @PathVariable long id
    ) {
        return "User ID: " + id;
    }

    private final Map<Integer, UserEntity> userList = new HashMap<>(Map.of(1, new UserEntity(1, "Kacper"), 2, new UserEntity(2, "Michal"),3, new UserEntity(3, "Szymon")));


    @GetMapping("/users/all")
    public ResponseEntity<Collection<UserEntity>> getApiUser2() {
        return ResponseEntity.ok(userList.values());
    }

    @RequestMapping("/users/{id}/get")
    @ResponseBody
    public ResponseEntity<UserEntity> addUsers(
            @PathVariable("id") int id
    ) {
        return ResponseEntity.of(Optional.ofNullable(userList.get(id)));
    }
    @RequestMapping("/users/{id}/remove")
    @ResponseBody
    public ResponseEntity<UserEntity> removeUsers(
            @PathVariable("id") int id
    ) {
        return ResponseEntity.of(Optional.ofNullable(userList.remove(id)));
    }

    @RequestMapping("/users/add")
    @ResponseBody
    public Object ApiAddUser(@RequestParam String name){
        Integer id = userList.size() + 1;
        UserEntity newUser = new UserEntity(id,name);
        userList.put(id, newUser);
        return ResponseEntity.of(Optional.ofNullable(userList.put(id,newUser)));
    }
}

