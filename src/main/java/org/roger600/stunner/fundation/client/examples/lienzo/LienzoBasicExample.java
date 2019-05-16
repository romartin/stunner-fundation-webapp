package org.roger600.stunner.fundation.client.examples.lienzo;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import elemental2.dom.Element;

@Dependent
public class LienzoBasicExample implements LienzoExample {

    private final LienzoBasicExampleView view;

    @Inject
    public LienzoBasicExample(LienzoBasicExampleView view) {
        this.view = view;
    }

    @Override
    public String getName() {
        return "Basic";
    }

    @Override
    public Element getView() {
        return view.asElement();
    }
}
