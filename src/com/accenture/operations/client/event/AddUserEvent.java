package com.accenture.operations.client.event;

import com.google.gwt.event.shared.GwtEvent;

public class AddUserEvent extends GwtEvent<AddUserEventHandler> {
    public static Type<AddUserEventHandler> TYPE = new Type<AddUserEventHandler>();

    @Override
    public Type<AddUserEventHandler> getAssociatedType() {
        return TYPE;
    }

    @Override
    protected void dispatch(AddUserEventHandler handler) {
        handler.onAddUser(this);
    }
}
