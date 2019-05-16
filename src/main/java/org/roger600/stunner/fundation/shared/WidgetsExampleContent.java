package org.roger600.stunner.fundation.shared;

import org.jboss.errai.common.client.api.annotations.MapsTo;
import org.jboss.errai.common.client.api.annotations.Portable;

/**
 * Some example of an Errai portable type.
 */
@Portable
public class WidgetsExampleContent {

    private final String buttonText;

    public WidgetsExampleContent(@MapsTo("buttonText") String buttonText) {
        this.buttonText = buttonText;
    }

    public String getButtonText() {
        return buttonText;
    }
}
