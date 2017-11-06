package com.accenture.operations.client.presenter;

import com.accenture.operations.client.OperationsServiceAsync;
import com.accenture.operations.client.event.AddUserEvent;
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

public class EditUserPresenter implements Presenter {

    public interface Display {
        HasClickHandlers getSaveButton();
        HasClickHandlers getCancelButton();
        HasValue<String> getFirstName();
        HasValue<String> getLastName();
        HasValue<String> getEmailAddress();
        Widget asWidget();
    }

    private User user;
    private OperationsServiceAsync rpcService;
    private final HandlerManager eventBus;
    private final Display display;

    public EditUserPresenter(OperationsServiceAsync rpcService, HandlerManager eventBus, Display display) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.user = new User();
        this.display = display;
        bind();
    }

    public EditUserPresenter(OperationsServiceAsync rpcService, HandlerManager eventBus, Display display, String userName) {
        this.rpcService = rpcService;
        this.eventBus = eventBus;
        this.display = display;
        bind();

        rpcService.getUser(userName, new AsyncCallback<User>() {
            public void onSuccess(User result) {
                user = result;
                EditUserPresenter.this.display.getFirstName().setValue(user.getName());
                EditUserPresenter.this.display.getLastName().setValue(user.getSurname());
                EditUserPresenter.this.display.getEmailAddress().setValue(user.getEmail());
            }

            public void onFailure(Throwable caught) {
                Window.alert("Error retrieving contact");
            }
        });

    }

    public void bind() {
        this.display.getSaveButton().addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                doSave();
            }
        });
    }

    private void doSave() {
        user.setName(display.getFirstName().getValue());
        user.setSurname(display.getLastName().getValue());
        user.setEmail(display.getEmailAddress().getValue());

        rpcService.addUser(user, new AsyncCallback<User>() {
            public void onSuccess(User result) {
                eventBus.fireEvent(new AddUserEvent());
            }
            public void onFailure(Throwable caught) {
                Window.alert("Error updating contact");
            }
        });
    }

    @Override
    public void go(HasWidgets container) {
        container.clear();
        container.add(display.asWidget());
    }
}
