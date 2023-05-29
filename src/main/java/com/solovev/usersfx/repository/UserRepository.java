package com.solovev.usersfx.repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solovev.usersfx.model.User;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Class to download Users Data from URL
 */
public class UserRepository {
    private List<User> users = new ArrayList<>();
    private final ObjectMapper mapper = new ObjectMapper();

    public UserRepository() {
    }
    public UserRepository(File file) throws IOException {
        users = mapper.readValue(file, new TypeReference<>() {
        });
    }

    /**
     * Constructor to read users data from given URL
     *
     * @param url reads data from
     */
    public UserRepository(URL url) throws IOException {
        this.users = mapper.readValue(url, new TypeReference<>() {
        });
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users);
    }

    /**
     * Method saves users from repository to the file
     * @param file to store users in JSON format
     * @throws IOException
     */
    public void save(File file) throws IOException {
        mapper.writeValue(file,users);
    }

    public void addUser(User user){
        users.add(user);
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "users=" + users +
                ", mapper=" + mapper +
                '}';
    }
}
