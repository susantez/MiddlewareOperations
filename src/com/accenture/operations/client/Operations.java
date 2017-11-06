package com.accenture.operations.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.HandlerManager;
import com.google.gwt.user.client.ui.*;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.DOM;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;

/**
 * Entry point classes define <code>onModuleLoad()</code>
 */
public class Operations implements EntryPoint {

    /**
     * This is the entry point method.
     */
    public void onModuleLoad() {
        OperationsServiceAsync rpcService = GWT.create(OperationsService.class);
        HandlerManager eventBus = new HandlerManager(null);
        AppController appViewer = new AppController(eventBus, rpcService);
        appViewer.go(RootPanel.get());
    }
}
