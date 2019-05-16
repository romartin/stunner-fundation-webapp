package org.roger600.stunner.fundation.client.examples.widgets;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.google.gwt.user.client.ui.IsWidget;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.common.client.api.RemoteCallback;
import org.jboss.errai.common.client.ui.ElementWrapperWidget;
import org.roger600.stunner.fundation.shared.WidgetsExampleContent;
import org.roger600.stunner.fundation.shared.WidgetsExampleService;

/**
 * Some example that shows how to manage:
 * - Errai UI -> To bind the view using Elemental2 (jsinterop)
 * - Errai RPC -> For calling server side services
 */
@Dependent
public class WidgetsExample {

    private final Caller<WidgetsExampleService> serviceCaller;
    private final WidgetsExampleView view;

    @Inject
    public WidgetsExample(Caller<WidgetsExampleService> serviceCaller,
                          WidgetsExampleView view) {
        this.serviceCaller = serviceCaller;
        this.view = view;
    }

    @PostConstruct
    public void init() {
        view.init(this);
    }

    void onButtonClick() {
        serviceCaller.call(new RemoteCallback<WidgetsExampleContent>() {
            @Override
            public void callback(WidgetsExampleContent response) {
                view.setButtonText(response.getButtonText());
            }
        }).getContent();
    }

    public IsWidget getViewAsWidget() {
        return ElementWrapperWidget.getWidget(getView().getElement());
    }

    public WidgetsExampleView getView() {
        return view;
    }
}
