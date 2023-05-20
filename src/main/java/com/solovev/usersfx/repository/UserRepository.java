package com.solovev.usersfx.repository;

import com.solovev.usersfx.model.User;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 * Class to download Users Data from URL
 */
public class UserRepository {
    ArrayList<User> users = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    public UserRepository() {
    }

    /**
     * Constructor to read users data from given URL
     * @param url reads data from
     */
    public UserRepository(URL url) throws IOException {
        this.users = mapper.readValue(url,new TypeReference<>(){});
    }

    public ArrayList<User> getUsers() {
        return new ArrayList<>(users);
    }

    @Override
    public String toString() {
        return "UserRepository{" +
                "users=" + users +
                ", mapper=" + mapper +
                '}';
    }
}
