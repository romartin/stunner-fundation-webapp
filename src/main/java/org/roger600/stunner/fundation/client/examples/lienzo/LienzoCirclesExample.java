package org.roger600.stunner.fundation.client.examples.lienzo;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import elemental2.dom.Element;

@Dependent
public class LienzoCirclesExample implements LienzoExample {

    private final LienzoCirclesExampleView view;

    @Inject
    public LienzoCirclesExample(LienzoCirclesExampleView view) {
        this.view = view;
    }

    @Override
    public String getName() {
        return "Circles";
    }

    @Override
    public Element getView() {
        return view.asElement();
    }
}
