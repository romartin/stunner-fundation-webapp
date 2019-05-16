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

import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.user.client.ui.IsWidget;
import elemental2.dom.Element;
import elemental2.dom.HTMLButtonElement;
import elemental2.dom.HTMLDivElement;
import elemental2.dom.HTMLDocument;
import elemental2.dom.HTMLOptionElement;
import elemental2.dom.HTMLSelectElement;
import org.jboss.errai.common.client.dom.elemental2.Elemental2DomUtil;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Dependent
@Templated
public class LienzoExamplesView implements IsElement {

    @Inject
    @DataField
    HTMLSelectElement examplesSelector;

    @Inject
    @DataField
    HTMLButtonElement goToExampleButton;

    @Inject
    @DataField
    HTMLDivElement exampleContainer;

    @Inject
    HTMLDocument document;

    @Inject
    Elemental2DomUtil elemental2DomUtil;

    private LienzoExamples presenter;

    void init(LienzoExamples presenter) {
        this.presenter = presenter;
    }

    public LienzoExamplesView addOption(String name, boolean selected) {
        HTMLOptionElement optionElement = createOption(name, selected);
        examplesSelector.add(optionElement);
        return this;
    }

    private HTMLOptionElement createOption(String name, boolean selected) {
        HTMLOptionElement optionElement = (HTMLOptionElement) document.createElement("option");
        optionElement.value = name;
        optionElement.text = name;
        optionElement.selected = selected;
        return optionElement;
    }

    public LienzoExamplesView set(final Element element) {
        exampleContainer.appendChild(element);
        return this;
    }

    public LienzoExamplesView set(final IsWidget widget) {
        elemental2DomUtil.appendWidgetToElement(exampleContainer, widget.asWidget());
        return this;
    }

    public LienzoExamplesView clear() {
        if (null != exampleContainer.firstChild) {
            exampleContainer.removeChild(exampleContainer.firstChild);
        }
        return this;
    }

    @EventHandler("goToExampleButton")
    public void onGoClick(ClickEvent event) {
        presenter.onGoClick(getCurrentExampleName());
    }

    private String getCurrentExampleName() {
        return examplesSelector.options.item(examplesSelector.selectedIndex).textContent;
    }

    @PreDestroy
    public void destroy() {
        presenter = null;
    }
}
