package org.roger600.stunner.fundation.backend;

import javax.enterprise.context.ApplicationScoped;

import org.jboss.errai.bus.server.annotations.Service;
import org.roger600.stunner.fundation.shared.WidgetsExampleContent;
import org.roger600.stunner.fundation.shared.WidgetsExampleService;

/**
 * The default WidgetsExampleService implementation.
 * Only present at server side.
 * It returns a <code>@Portable</code> type
 */
@Service
@ApplicationScoped
public class WidgetsExampleServiceImpl implements WidgetsExampleService {

    public static final String CONTENT_TEXT = "Some Button Text From Server";

    @Override
    public WidgetsExampleContent getContent() {
        return new WidgetsExampleContent(CONTENT_TEXT);
    }
}
