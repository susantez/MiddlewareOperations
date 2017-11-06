package com.accenture.operations.client.presenter;

import com.accenture.operations.client.OperationsServiceAsync;
import com.accenture.operations.client.event.AddUserEvent;
import com.accenture.operations.client.event.LoginEvent;
import com.accenture.operations.shared.User;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.HasWidgets;
import com.google.gwt.user.client.ui.Widget;

public class LoginPresenter implements Presenter {

    public interface Display {
        HasClickHandlers getLoginButton();
        HasValue<String> getUserName();
        HasValue<String> getPassword();
        Widget asWidget();
    }

    private OperationsServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final LoginPresenter.Display display;

    public LoginPresenter(OperationsServiceAsync rpcService, HandlerManager eventBus, Display display) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = display;

        bind();
    }

    public void bind() {
        this.display.getLoginButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                doLogin();
            }
        });
    }

    private void doLogin() {
        rpcService.login(display.getUserName().getValue(), display.getPassword().getValue(), new AsyncCallback<User>() {
            public void onSuccess(User result) {
                eventBus.fireEvent(new LoginEvent());
            }
            public void onFailure(Throwable caught) {
                Window.alert("Error during login operation");
            }
        });
    }

    @Override
    public void go(HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }
}
