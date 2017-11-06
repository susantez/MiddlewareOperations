package com.accenture.operations.client.view;

import com.accenture.operations.client.presenter.LoginPresenter;
import com.google.gwt.event.dom.client.HasClickHandlers;
import com.google.gwt.user.client.ui.*;

public class LoginView extends Composite implements LoginPresenter.Display {

    private final TextBox userName;
    private final PasswordTextBox password;
    private final Button loginButton;

    public LoginView() {
        DecoratorPanel contentDetailsDecorator = new DecoratorPanel();
        contentDetailsDecorator.setWidth("18em");
        initWidget(contentDetailsDecorator);

        VerticalPanel contentDetailsPanel = new VerticalPanel();
        contentDetailsPanel.setWidth("100%");


        userName = new TextBox();
        userName.setName("User Name");
        password = new PasswordTextBox();
        loginButton = new Button("Login");

        HorizontalPanel menuPanel = new HorizontalPanel();
        menuPanel.add(userName);
        menuPanel.add(password);
        menuPanel.add(loginButton);
        contentDetailsPanel.add(menuPanel);
        contentDetailsDecorator.add(contentDetailsPanel);
    }

    @Override
    public HasClickHandlers getLoginButton() {
        return this.loginButton;
    }

    @Override
    public HasValue<String> getUserName() {
        return this.userName;
    }

    @Override
    public HasValue<String> getPassword() {
        return this.password;
    }

    public Widget asWidget() {
        return this;
    }
}
