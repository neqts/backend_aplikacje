package com.example.demo;

import com.example.demo.PaginatedResult;
import com.example.demo.UserEntity;
import com.example.demo.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.naming.directory.InvalidAttributeIdentifierException;


@Controller
public class UsersController {

    @Autowired
    private UsersService usersService;


    @RequestMapping("/api/users")
    @ResponseBody
    public ResponseEntity<PaginatedResult> ApiGetUsers(@RequestParam(name="page-number", required=false) Integer pageNumber, @RequestParam(name="page-size", required=false) Integer pageSize) {
        PaginatedResult users;
        try{
            users = usersService.getUsers(pageNumber, pageSize);
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(users);
    }

    @RequestMapping("/api/users/{id}/get")
    @ResponseBody
    public ResponseEntity<UserEntity> ApiGetUser(@PathVariable Integer id) {

        try {
            UserEntity user = this.usersService.getUser(id);
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(user);
        } catch (InvalidAttributeIdentifierException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/api/users/create")
    @ResponseBody
    public Object ApiAddUser(@RequestBody UserEntity newUser){
        return this.usersService.addUser(newUser);
    }

    @PutMapping("/api/users/{id}/update")
    @ResponseBody
    public ResponseEntity<UserEntity> ApiUpdateUser(@PathVariable Integer id, @RequestBody UserEntity updatedUser){
        UserEntity result;
        try {
            result = this.usersService.updateUser(id, updatedUser);
        } catch(IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
    }

    @RequestMapping("/api/users/{id}/remove")
    @ResponseBody
    public Object ApiRemoveUser(@PathVariable Integer id) {
        boolean result = this.usersService.removeUser(id);
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(result);
    }

}
//
//3. Podczas uruchamiania aplikacji wczytuj użytkowników z pliku, podczas zatrzymywania zapisuj:
//@Service