package com.accenture.operations.client;

import com.accenture.operations.shared.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("operationsService")
public interface OperationsService extends RemoteService {
    User addUser(User user);
    Boolean deleteUser(String userName);
    User getUser(String userName);
    User login(String userName, String password);
}
