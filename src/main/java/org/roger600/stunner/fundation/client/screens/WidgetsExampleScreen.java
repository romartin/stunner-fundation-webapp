/*
 * Copyright 2019 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.roger600.stunner.fundation.client.screens;

import java.util.function.Consumer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.IsWidget;
import org.roger600.stunner.fundation.client.examples.widgets.WidgetsExample;
import org.uberfire.client.annotations.WorkbenchContextId;
import org.uberfire.client.annotations.WorkbenchMenu;
import org.uberfire.client.annotations.WorkbenchPartTitle;
import org.uberfire.client.annotations.WorkbenchPartView;
import org.uberfire.client.annotations.WorkbenchScreen;
import org.uberfire.lifecycle.OnClose;
import org.uberfire.lifecycle.OnOpen;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.workbench.model.menu.MenuFactory;
import org.uberfire.workbench.model.menu.Menus;

@Dependent
@WorkbenchScreen(identifier = WidgetsExampleScreen.SCREEN_ID)
public class WidgetsExampleScreen {

    public static final String SCREEN_ID = "WidgetsExampleScreen";

    @Inject
    private WidgetsExample widgetsExample;

    private Menus menu = null;

    @PostConstruct
    public void init() {
    }

    @OnStartup
    public void onStartup(final PlaceRequest placeRequest) {
        this.menu = makeMenuBar();
    }

    private Menus makeMenuBar() {
        return MenuFactory
                .newTopLevelMenu("Command1")
                .respondsWith(() -> Window.alert("Command 1!"))
                .endMenu()
                .build();
    }

    @OnOpen
    public void onOpen() {
    }

    @OnClose
    public void onClose() {
    }

    @WorkbenchMenu
    public void getMenus(final Consumer<Menus> menusConsumer) {
        menusConsumer.accept(menu);
    }

    @WorkbenchPartTitle
    public String getTitle() {
        return "Widgets example";
    }

    @WorkbenchPartView
    public IsWidget getWidget() {
        return widgetsExample.getViewAsWidget();
    }

    @WorkbenchContextId
    public String getMyContextRef() {
        return "WidgetsExampleScreenContext";
    }
}
