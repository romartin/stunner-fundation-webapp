package org.roger600.stunner.fundation.client.examples.lienzo;

import java.util.function.BiConsumer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.ait.lienzo.client.core.animation.AnimationCallback;
import com.ait.lienzo.client.core.animation.AnimationProperties;
import com.ait.lienzo.client.core.animation.AnimationProperty;
import com.ait.lienzo.client.core.animation.AnimationTweener;
import com.ait.lienzo.client.core.animation.IAnimation;
import com.ait.lienzo.client.core.animation.IAnimationHandle;
import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.core.shape.Rectangle;
import com.ait.lienzo.client.core.shape.Shape;
import com.ait.lienzo.client.core.shape.Text;
import com.ait.lienzo.client.core.types.BoundingBox;
import com.ait.lienzo.shared.core.types.ColorName;
import com.google.gwt.event.shared.HandlerRegistration;
import elemental2.dom.Element;

@Dependent
public class LienzoBasicExampleView {

    private final LienzoExampleView view;

    @Inject
    public LienzoBasicExampleView(LienzoExampleView view) {
        this.view = view;
    }

    @PostConstruct
    public void init() {
        build();
    }

    public Element asElement() {
        return view.getElement();
    }

    private void build() {
        final Layer layer = getLayer();

        // Some rectangle.
        final Rectangle rectangle =
                new Rectangle(100, 100)
                        .setFillColor(ColorName.INDIANRED)
                        .setStrokeColor(ColorName.BLACK)
                        .setStrokeWidth(1.5)
                        .setStrokeAlpha(0.6)
                        .setX(50)
                        .setY(50);
        layer.add(rectangle);
        addClickMeSupport(rectangle,
                          () -> rectangle.animate(AnimationTweener.ELASTIC,
                                                  AnimationProperties.toPropertyList(AnimationProperty.Properties.WIDTH(300),
                                                                                     AnimationProperty.Properties.HEIGHT(300)),
                                                  ANIMATION_DURATION));
        // Some circle.
        final Circle circle =
                new Circle(75)
                        .setFillColor(ColorName.BLUE)
                        .setFillAlpha(0.8)
                        .setX(450)
                        .setY(150);
        layer.add(circle);
        addClickMeSupport(circle,
                          () -> circle.animate(AnimationTweener.ELASTIC,
                                               AnimationProperties.toPropertyList(AnimationProperty.Properties.RADIUS(200)),
                                               ANIMATION_DURATION));

        layer.draw();
    }

    private static Text buildClickMeInstance() {
        return new Text("Click Me!")
                .setFontFamily("Verdana")
                .setFontSize(10);
    }

    private static double ANIMATION_DURATION = 1500;

    private void addClickMeSupport(final Shape<?> node,
                                   final Runnable onClick) {
        addClickMeSupport(node,
                          (text, registration) -> {
                              text.setListening(false);
                              text.animate(AnimationTweener.LINEAR,
                                           AnimationProperties.toPropertyList(AnimationProperty.Properties.ALPHA(0)),
                                           250,
                                           new AnimationCallback() {
                                               @Override
                                               public void onClose(IAnimation animation, IAnimationHandle handle) {
                                                   super.onClose(animation, handle);
                                                   registration.removeHandler();
                                                   text.removeFromParent();
                                                   onClick.run();
                                               }
                                           });
                          });
    }

    private void addClickMeSupport(final Shape<?> node,
                                   final BiConsumer<Text, HandlerRegistration> onClick) {
        final BoundingBox bb = node.getComputedBoundingPoints().getBoundingBox();
        final double nodeCenterX = bb.getWidth() / 2;
        final double nodeCenterY = bb.getHeight() / 2;
        final Text text = buildClickMeInstance()
                .setX(bb.getX() + nodeCenterX - 25)
                .setY(bb.getY() + nodeCenterY - 5);
        final HandlerRegistration[] handlerRegistrations = new HandlerRegistration[1];
        handlerRegistrations[0] = node.addNodeMouseClickHandler(event -> onClick.accept(text, handlerRegistrations[0]));
        getLayer().add(text.moveToTop());
    }

    private Layer getLayer() {
        return view.getLienzoPanel().getLayer();
    }
}
