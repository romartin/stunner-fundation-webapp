package org.roger600.stunner.fundation.client.examples.lienzo;

import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.client.core.shape.IPrimitive;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.core.shape.Text;
import com.ait.lienzo.client.core.shape.Viewport;
import com.ait.lienzo.client.widget.panel.LienzoBoundsPanel;
import com.ait.lienzo.shared.core.types.ColorName;
import com.ait.lienzo.test.LienzoMockitoTestRunner;
import com.ait.tooling.nativetools.client.collection.NFastArrayList;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

/**
 * An example of unit testing for Lienzo types.
 */
@RunWith(LienzoMockitoTestRunner.class)
public class LienzoBasicExampleViewTest {

    @Mock
    private LienzoExampleView view;

    @Mock
    private LienzoBoundsPanel panel;

    private LienzoBasicExampleView tested;
    private Layer layer;

    @Before
    public void setup() {
        layer = new Layer();
        new Viewport().getScene().add(layer);
        when(view.getLienzoPanel()).thenReturn(panel);
        when(panel.getLayer()).thenReturn(layer);

        tested = new LienzoBasicExampleView(view);
    }

    @Test
    public void testBuildInstances() {
        tested.init();
        NFastArrayList<IPrimitive<?>> childNodes = layer.getChildNodes();
        assertNotNull(childNodes);
        assertFalse(childNodes.isEmpty());
        assertEquals(4, childNodes.size());
        // Assertions for rectangle attributes.
        Rectangle rectangle = (Rectangle) childNodes.get(0);
        assertNotNull(rectangle);
        Text rectangleText = (Text) childNodes.get(1);
        assertNotNull(rectangleText);
        assertEquals(50d, rectangle.getX(), 0d);
        assertEquals(50d, rectangle.getY(), 0d);
        assertEquals(100d, rectangle.getWidth(), 0d);
        assertEquals(100d, rectangle.getHeight(), 0d);
        assertEquals(ColorName.INDIANRED.getColorString(), rectangle.getFillColor());
        assertEquals(ColorName.BLACK.getColorString(), rectangle.getStrokeColor());
        assertEquals(1.5d, rectangle.getStrokeWidth(), 0d);
        assertEquals(0.6d, rectangle.getStrokeAlpha(), 0d);
        // Assertions for circle attributes.
        Circle circle = (Circle) childNodes.get(2);
        assertNotNull(circle);
        Text circleText = (Text) childNodes.get(3);
        assertNotNull(circleText);
        assertEquals(450d, circle.getX(), 0d);
        assertEquals(150d, circle.getY(), 0d);
        assertEquals(75d, circle.getRadius(), 0d);
        assertEquals(ColorName.BLUE.getColorString(), circle.getFillColor());
        assertEquals(0.8d, circle.getFillAlpha(), 0d);
    }
}
