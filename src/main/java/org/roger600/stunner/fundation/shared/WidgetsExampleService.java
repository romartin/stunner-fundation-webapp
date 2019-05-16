package org.roger600.stunner.fundation.shared;

import org.jboss.errai.bus.server.annotations.Remote;

/**
 * An example of an Errai service.
 * <p>
 * Notice it's present on the <code>shared</code> package, so:
 * - server side visible -> in order to create the service implementation
 * - client side visible -> in order to call the service
 * <p>
 * Notice it returns a <code>@Portable</code> type (<code>WidgetsExampleContent</code>), so the marshalling is
 * being provided out-of-the-box by Errai.
 */
@Remote
public interface WidgetsExampleService {

    WidgetsExampleContent getContent();
}
