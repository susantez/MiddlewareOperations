package com.accenture.operations.server;

import com.accenture.operations.client.OperationsService;
import com.accenture.operations.shared.User;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import java.util.HashMap;

public class OperationsServiceImpl extends RemoteServiceServlet implements OperationsService {

    private final HashMap<String, User> users = new HashMap<String, User>();

    @Override
    public User addUser(User user) {
        users.put(user.getUserName(), user);
        return user;
    }

    @Override
    public Boolean deleteUser(String userName) {
        users.remove(userName);
        return true;
    }

    @Override
    public User getUser(String userName) {
        return users.get(userName);
    }

    @Override
    public User login(String userName, String password) {
        //@TODO implement LDAP authentication
        return new User();
    }
}