package com.accenture.operations.client;

import com.accenture.operations.client.event.AddUserEvent;
import com.accenture.operations.client.event.AddUserEventHandler;
import com.accenture.operations.client.event.LoginEvent;
import com.accenture.operations.client.event.LoginEventHandler;
import com.accenture.operations.client.presenter.EditUserPresenter;
import com.accenture.operations.client.presenter.LoginPresenter;
import com.accenture.operations.client.presenter.Presenter;
import com.accenture.operations.client.view.EditUserView;
import com.accenture.operations.client.view.LoginView;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.RunAsyncCallback;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.HasWidgets;

public class AppController implements Presenter, ValueChangeHandler<String> {
    private final HandlerManager eventBus;
    private final OperationsServiceAsync rpcService;

    private HasWidgets container;
    private EditUserView editUserView = null;
    private LoginView loginView = null;

    public AppController(HandlerManager eventBus, OperationsServiceAsync rpcService) {
        this.eventBus = eventBus;
        this.rpcService = rpcService;
        bind();
    }

    public void bind() {
        History.addValueChangeHandler(this);

        eventBus.addHandler(AddUserEvent.TYPE,
                new AddUserEventHandler() {
                    @Override
                    public void onAddUser(AddUserEvent event) {
                        doAddNewUser();
                    }
                });
        eventBus.addHandler(LoginEvent.TYPE,
                new LoginEventHandler() {
                    @Override
                    public void onLogin(LoginEvent event) {
                        doLogin();
                    }
                });
    }

    private void doAddNewUser() {
        History.newItem("add");
    }

    private void doLogin() {
        History.newItem("appList");
    }

    @Override
    public void go(HasWidgets container) {
        this.container = container;

        if ("".equals(History.getToken())) {
            History.newItem("login");
        }
        else {
            History.fireCurrentHistoryState();
        }
    }

    @Override
    public void onValueChange(ValueChangeEvent<String> event) {
        String token = event.getValue();

        if (token != null) {
           if (token.equals("add") || token.equals("appList")) {
                GWT.runAsync(new RunAsyncCallback() {
                    public void onFailure(Throwable caught) {
                    }

                    public void onSuccess() {
                        // lazily initialize our views, and keep them around to be reused
                        //
                        if (editUserView == null) {
                            editUserView = new EditUserView();

                        }
                        new EditUserPresenter(rpcService, eventBus, editUserView).
                                go(container);
                    }
                });
            } else if (token.equals("login")) {
               GWT.runAsync(new RunAsyncCallback() {

                   @Override
                   public void onFailure(Throwable reason) {

                   }

                   @Override
                   public void onSuccess() {
                       if (loginView == null) {
                           loginView = new LoginView();
                       }
                       new LoginPresenter(rpcService, eventBus, loginView).go(container);
                   }
               });
           }
        }
    }
}
