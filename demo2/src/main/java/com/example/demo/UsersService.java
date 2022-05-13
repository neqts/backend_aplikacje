package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.naming.directory.InvalidAttributeIdentifierException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.lang.*;

@Service
public class UsersService {
    private static Integer DEFAULT_PAGE_NUMBER = 1;
    private static Integer DEFAULT_PAGE_SIZE = 5;
    private Map<Integer, UserEntity> usersMap = new HashMap<>();
//    private Map<Integer, UserEntity> usersMap = new HashMap<>() {{
//        put(0, new UserEntity(0, "Jack", 28));
//        put(1, new UserEntity(1, "Adam", 22));
//        put(2, new UserEntity(2, "Emma", 15));
//        put(3, new UserEntity(3, "Bob", 18));
//        put(4, new UserEntity(4, "John", 20));
//        put(5, new UserEntity(5, "Michael", 25));
//        put(6, new UserEntity(6, "Ann", 21));
//    }};

    @PostConstruct
    private void onCreate() {
        File file = new File("users.txt");
        int length = (int) file.length();
        try (
                FileInputStream stream = new FileInputStream(file);
                InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
            char[] data = new char[length];
            int readBytes = reader.read(data, 0, length);
            if (readBytes != length) {
                throw new IOException("File reading error - wrong length");
            }
            String usersJson = new String(data);
            ObjectMapper objectMapper = new ObjectMapper();

            TypeReference<HashMap<Integer,UserEntity>> typeRef
                    = new TypeReference<HashMap<Integer,UserEntity>>() {};

            this.usersMap = objectMapper.readValue(usersJson, typeRef);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PreDestroy
    private void onDestroy() {
        String json = null;
        try {
            json = new ObjectMapper().writeValueAsString(this.usersMap);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        try (FileOutputStream stream = new FileOutputStream("users.txt");
             OutputStreamWriter writer = new OutputStreamWriter(stream, StandardCharsets.UTF_8)
        ) {
            writer.write(json);
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public PaginatedResult getUsers(Integer pageNumber, Integer pageSize) {
        if (pageNumber == null || pageNumber < 1) {
            pageNumber = DEFAULT_PAGE_NUMBER;
        }
        if (pageSize == null || pageSize < 1 || pageSize > 100) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        Integer totalCount = usersMap.size();
        Integer pagesCount = (int) Math.round(Math.ceil((double) totalCount / pageSize));

        var usersArrayList = new ArrayList<UserEntity>(usersMap.values());
        int offset = (pageNumber - 1) * pageSize;

        List<UserEntity> paginatedUsersList;
        try {
            paginatedUsersList = usersArrayList.subList(offset, offset + pageSize);
        } catch (IndexOutOfBoundsException e) {
            paginatedUsersList = usersArrayList.subList(offset, usersArrayList.size());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(e);
        }


        return new PaginatedResult(pageNumber, pagesCount, pageSize, totalCount, paginatedUsersList);
    }

    public UserEntity getUser(Integer id) throws InvalidAttributeIdentifierException {
        if (usersMap.containsKey(id)) {
            return usersMap.get(id);
        } else {
            throw new InvalidAttributeIdentifierException("User not found.");
        }
    }

    public Object addUser(UserEntity newUser) {
        Integer id = usersMap.size();
        newUser.setId(id);
        usersMap.put(id, newUser);

        return newUser;
    }

    public UserEntity updateUser(Integer id, UserEntity updatedUser) throws IllegalArgumentException {
        if (usersMap.containsKey(id)) {
            updatedUser.setId(id);
            usersMap.replace(id, updatedUser);
            return updatedUser;
        } else {
            throw new IllegalArgumentException(String.format("User with id %d not found.", id));
        }
    }

    public boolean removeUser(Integer id) {
        if (usersMap.containsKey(id)) {
            usersMap.remove(id);
            return true;
        }
        return false;
    }


}
