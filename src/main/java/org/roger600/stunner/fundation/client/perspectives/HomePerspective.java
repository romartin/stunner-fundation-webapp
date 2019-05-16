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

package org.roger600.stunner.fundation.client.perspectives;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.roger600.stunner.fundation.client.examples.lienzo.LienzoExample;
import org.roger600.stunner.fundation.client.screens.WidgetsExampleScreen;
import org.roger600.stunner.fundation.client.screens.LienzoExamplesScreen;
import org.uberfire.client.annotations.Perspective;
import org.uberfire.client.annotations.WorkbenchPerspective;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.workbench.panels.impl.MultiListWorkbenchPanelPresenter;
import org.uberfire.lifecycle.OnStartup;
import org.uberfire.mvp.PlaceRequest;
import org.uberfire.mvp.impl.DefaultPlaceRequest;
import org.uberfire.workbench.model.CompassPosition;
import org.uberfire.workbench.model.PanelDefinition;
import org.uberfire.workbench.model.PerspectiveDefinition;
import org.uberfire.workbench.model.impl.PanelDefinitionImpl;
import org.uberfire.workbench.model.impl.PartDefinitionImpl;
import org.uberfire.workbench.model.impl.PerspectiveDefinitionImpl;

@ApplicationScoped
@WorkbenchPerspective(identifier = HomePerspective.PERSPECTIVE_ID, isTransient = false, isDefault = true)
public class HomePerspective {

    public static final String PERSPECTIVE_ID = "HomePerspective";

    @Inject
    PlaceManager placeManager;

    private PanelDefinition lienzoExamplesPanel;
    private PlaceRequest placeRequest;

    @OnStartup
    public void onStartup(final PlaceRequest placeRequest) {
        this.placeRequest = placeRequest;
    }

    @Perspective
    public PerspectiveDefinition buildPerspective() {
        PerspectiveDefinition perspective = new PerspectiveDefinitionImpl(MultiListWorkbenchPanelPresenter.class.getName());
        perspective.setName("Home");
        perspective.getRoot().addPart(new PartDefinitionImpl(new DefaultPlaceRequest(WidgetsExampleScreen.SCREEN_ID)));
        lienzoExamplesPanel = new PanelDefinitionImpl(MultiListWorkbenchPanelPresenter.class.getName());
        lienzoExamplesPanel.setMinHeight(LienzoExample.HEIGHT + 50);
        lienzoExamplesPanel.setHeight(LienzoExample.HEIGHT + 60);
        lienzoExamplesPanel.addPart(new PartDefinitionImpl(new DefaultPlaceRequest(LienzoExamplesScreen.SCREEN_ID)));
        perspective.getRoot().insertChild(CompassPosition.NORTH, lienzoExamplesPanel);
        return perspective;
    }
}
