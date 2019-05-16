package org.roger600.stunner.fundation.client.examples.widgets;

import com.ait.lienzo.test.LienzoMockitoTestRunner;
import org.jboss.errai.common.client.api.Caller;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.roger600.stunner.fundation.shared.WidgetsExampleContent;
import org.roger600.stunner.fundation.shared.WidgetsExampleService;
import org.uberfire.mocks.CallerMock;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(LienzoMockitoTestRunner.class)
public class WidgetsExampleTest {

    @Mock
    private WidgetsExampleService service;

    @Mock
    private WidgetsExampleView view;

    private WidgetsExample tested;
    private Caller<WidgetsExampleService> serviceCaller;

    @Before
    public void setup() {
        serviceCaller = new CallerMock<>(service);
        tested = new WidgetsExample(serviceCaller, view);
    }

    @Test
    public void testInit() {
        tested.init();
        verify(view, times(1)).init(eq(tested));
    }

    @Test
    public void testOnButtonClick() {
        WidgetsExampleContent content = new WidgetsExampleContent("testText");
        when(service.getContent()).thenReturn(content);
        tested.onButtonClick();
        verify(view, times(1)).setButtonText("testText");
    }
}
