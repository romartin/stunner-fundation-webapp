package org.roger600.stunner.fundation.backend;

import org.junit.Before;
import org.junit.Test;
import org.roger600.stunner.fundation.shared.WidgetsExampleContent;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class WidgetsExampleServiceImplTest {

    private WidgetsExampleServiceImpl tested;

    @Before
    public void setup() {
        tested = new WidgetsExampleServiceImpl();
    }

    @Test
    public void testGetContent() {
        WidgetsExampleContent content = tested.getContent();
        assertNotNull(content);
        assertEquals(WidgetsExampleServiceImpl.CONTENT_TEXT, content.getButtonText());
    }
}
