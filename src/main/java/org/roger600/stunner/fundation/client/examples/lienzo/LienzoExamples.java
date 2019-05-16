package org.roger600.stunner.fundation.client.examples.lienzo;

import java.util.stream.StreamSupport;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Any;
import javax.inject.Inject;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.IsWidget;
import org.jboss.errai.common.client.ui.ElementWrapperWidget;
import org.jboss.errai.ioc.client.api.ManagedInstance;

@Dependent
public class LienzoExamples {

    static String OPTION_NONE = " ";

    private final LienzoExamplesView view;
    private final ManagedInstance<LienzoExample> examples; // We'll obtain the different LienzoExample types available via IoC / CDI
    private LienzoExample current;

    @Inject
    public LienzoExamples(LienzoExamplesView view,
                          @Any ManagedInstance<LienzoExample> examples) {
        this.view = view;
        this.examples = examples;
    }

    @PostConstruct
    public void init() {
        view.init(this);
        view.addOption(OPTION_NONE, true);
        examples.forEach(example -> view.addOption(example.getName(), false));
    }

    void onGoClick(String name) {
        setExample(name);
    }

    private void setExample(String name) {
        GWT.log("Opening example [" + name + "]");
        StreamSupport.stream(examples.spliterator(), false)
                .filter(example -> name.equals(example.getName()))
                .findFirst()
                .ifPresent(this::setCurrentExample);
    }

    private void setCurrentExample(LienzoExample example) {
        if (null != current) {
            examples.destroy(current);
        }
        current = example;
        updateView();
    }

    private void updateView() {
        view.clear().set(current.getView());
    }

    public LienzoExamplesView getView() {
        return view;
    }

    public IsWidget getViewAsWidget() {
        return ElementWrapperWidget.getWidget(getView().getElement());
    }

    @PreDestroy
    public void destroy() {
        view.clear();
        examples.destroyAll();
        current = null;
    }
}
