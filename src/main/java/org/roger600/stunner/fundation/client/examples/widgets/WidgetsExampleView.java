package org.roger600.stunner.fundation.client.examples.widgets;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.event.dom.client.ClickEvent;
import elemental2.dom.HTMLButtonElement;
import org.jboss.errai.ui.client.local.api.elemental2.IsElement;
import org.jboss.errai.ui.shared.api.annotations.DataField;
import org.jboss.errai.ui.shared.api.annotations.EventHandler;
import org.jboss.errai.ui.shared.api.annotations.Templated;

@Templated
@Dependent
public class WidgetsExampleView implements IsElement {

    @Inject
    @DataField
    HTMLButtonElement button;

    private WidgetsExample presenter;

    void init(WidgetsExample presenter) {
        this.presenter = presenter;
    }

    public void setButtonText(String value) {
        button.textContent = value;
    }

    @EventHandler("button")
    public void onButtonClick(ClickEvent event) {
        presenter.onButtonClick();
    }
}
