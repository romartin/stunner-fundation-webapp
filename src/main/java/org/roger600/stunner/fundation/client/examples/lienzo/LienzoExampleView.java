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

package org.roger600.stunner.fundation.client.examples.lienzo;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.widget.panel.LienzoBoundsPanel;
import com.ait.lienzo.client.widget.panel.impl.BoundsProviderFactory;
import com.ait.lienzo.client.widget.panel.scrollbars.ScrollablePanel;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import elemental2.dom.HTMLDivElement;
import org.jboss.errai.common.client.dom.elemental2.Elemental2DomUtil;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Dependent
@Templated
public class LienzoExampleView implements IsElement {

    @Inject
    @DataField
    HTMLDivElement lienzoPanelContainer;

    @Inject
    Elemental2DomUtil elemental2DomUtil;

    private LienzoBoundsPanel panel;

    @PostConstruct
    public void init() {
        GWT.log("Initialising Lienzo Panel...");
        panel = new ScrollablePanel(new BoundsProviderFactory.WiresBoundsProvider(), LienzoExample.WIDTH, LienzoExample.HEIGHT);
        final Layer layer = new Layer().setTransformable(true);
        panel.add(layer);
        add(panel);
    }

    private LienzoExampleView add(final IsWidget widget) {
        elemental2DomUtil.appendWidgetToElement(lienzoPanelContainer,
                                                widget.asWidget());
        return this;
    }

    public LienzoBoundsPanel getLienzoPanel() {
        return panel;
    }

    @PreDestroy
    public void destroy() {
        GWT.log("Destroying Lienzo Panel...");
        panel.destroy();
        panel = null;
    }
}
