package com.accenture.operations.client;

import com.accenture.operations.shared.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface OperationsServiceAsync {

    void addUser(User user, AsyncCallback<User> async);

    void deleteUser(String userName, AsyncCallback<Boolean> async);

    void getUser(String userName, AsyncCallback<User> async);

    void login(String userName, String password, AsyncCallback<User> async);
}
