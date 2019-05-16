package org.roger600.stunner.fundation.client.examples.lienzo;

import com.ait.lienzo.test.LienzoMockitoTestRunner;
import elemental2.dom.Element;
import org.jboss.errai.ioc.client.api.ManagedInstance;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.roger600.stunner.fundation.client.ManagedInstanceStub;

import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(LienzoMockitoTestRunner.class)
public class LienzoExamplesTest {

    private static final String EXAMPLE1 = "ex1";
    private static final String EXAMPLE2 = "ex2";

    @Mock
    private LienzoExamplesView view;

    @Mock
    private LienzoExample example1;

    @Mock
    private Element view1;

    @Mock
    private LienzoExample example2;

    @Mock
    private Element view2;

    private LienzoExamples tested;
    private ManagedInstance<LienzoExample> examples;

    @Before
    public void setup() {
        when(example1.getName()).thenReturn(EXAMPLE1);
        when(example1.getView()).thenReturn(view1);
        when(example2.getName()).thenReturn(EXAMPLE2);
        when(example2.getView()).thenReturn(view2);
        when(view.clear()).thenReturn(view); // Mocking the cascade pattern behavior.
        examples = spy(new ManagedInstanceStub<>(example1, example2));
        tested = new LienzoExamples(view, examples);
    }

    @Test
    public void testInit() {
        tested.init();
        verify(view, times(1)).init(eq(tested));
        verify(view, times(1)).addOption(eq(LienzoExamples.OPTION_NONE), eq(true));
        verify(view, times(1)).addOption(eq(EXAMPLE1), eq(false));
        verify(view, times(1)).addOption(eq(EXAMPLE2), eq(false));
    }

    @Test
    public void testDestroy() {
        tested.destroy();
        verify(view, times(1)).clear();
        verify(examples, times(1)).destroyAll();
    }

    @Test
    public void testGoToExample1() {
        tested.onGoClick(EXAMPLE1);
        verify(view, times(1)).clear();
        verify(view, times(1)).set(eq(view1));
    }

    @Test
    public void testGoToExample2() {
        tested.onGoClick(EXAMPLE2);
        verify(view, times(1)).clear();
        verify(view, times(1)).set(eq(view2));
    }

    @Test
    public void testGoToExample1AndThenToExample2() {
        tested.onGoClick(EXAMPLE1);
        tested.onGoClick(EXAMPLE2);
        verify(view, times(2)).clear();
        verify(view, times(1)).set(eq(view1));
        verify(view, times(1)).set(eq(view2));
        verify(examples, times(1)).destroy(eq(example1));
    }
}
