package org.roger600.stunner.fundation.client.examples.lienzo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;

import com.ait.lienzo.client.core.shape.Circle;
import com.ait.lienzo.client.core.shape.Layer;
import com.ait.lienzo.client.widget.panel.LienzoBoundsPanel;
import com.ait.lienzo.shared.core.types.Color;
import com.google.gwt.animation.client.AnimationScheduler;
import com.google.gwt.animation.client.AnimationScheduler.AnimationCallback;
import com.google.gwt.animation.client.AnimationScheduler.AnimationHandle;
import com.google.gwt.core.client.GWT;
import elemental2.dom.Element;

@Dependent
public class LienzoCirclesExampleView {

    private final LienzoExampleView view;
    private final List<MotionCircle> circles;
    private AnimationHandle animation;

    @Inject
    public LienzoCirclesExampleView(LienzoExampleView view) {
        this.view = view;
        this.circles = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        build();
    }

    @PreDestroy
    public void destroy() {
        animation.cancel();
        circles.clear();
    }

    public Element asElement() {
        return view.getElement();
    }

    private void build() {
        final LienzoBoundsPanel lienzoPanel = view.getLienzoPanel();
        final Layer layer = lienzoPanel.getLayer();

        layer.setListening(false);
        int total = GWT.isProdMode() ? 100 : 3;

        for (int i = 0; i < total; i++) {
            final MotionCircle circle = new MotionCircle(Math.max(40, Math.random() * 60));
            circle
                    .setAlpha(0.75)
                    .setX(generateValueWithinBoundary(LienzoExample.WIDTH))
                    .setY(generateValueWithinBoundary(LienzoExample.HEIGHT))
                    .setStrokeColor(Color.getRandomHexColor())
                    .setStrokeWidth(2)
                    .setFillColor(Color.getRandomHexColor());
            circles.add(circle);
            layer.add(circle);
        }

        animate(layer);
    }

    private static double generateValueWithinBoundary(final double value) {
        return Math.random() * value;
    }

    public void animate(final Layer layer) {
        animation = AnimationScheduler.get().requestAnimationFrame(new AnimationCallback() {
            @Override
            public void execute(double timestamp) {
                for (MotionCircle circle : circles) {
                    animateCircle(circle);
                }
                layer.draw();
                AnimationScheduler.get().requestAnimationFrame(this);
            }
        }, layer.getCanvasElement());
    }

    private void animateCircle(MotionCircle circle) {

        double x = circle.getX();
        double y = circle.getY();
        double r = circle.getRadius();

        if (x + circle.getxVelocity() + r > LienzoExample.WIDTH || x + circle.getxVelocity() - r < 0) {
            circle.setxVelocity(-circle.getxVelocity());
        }
        if (y + circle.getyVelocity() + r > LienzoExample.HEIGHT || y + circle.getyVelocity() - r < 0) {
            circle.setyVelocity(-circle.getyVelocity());
        }

        circle.setX(x + circle.getxVelocity());
        circle.setY(y + circle.getyVelocity());
    }

    private static final class MotionCircle extends Circle {

        private double xVelocity = Math.random() * 3;
        private double yVelocity = Math.random() * 3;

        public MotionCircle(double radius) {
            super(radius);
        }

        public double getxVelocity() {
            return xVelocity;
        }

        public void setxVelocity(double xVelocity) {
            this.xVelocity = xVelocity;
        }

        public double getyVelocity() {
            return yVelocity;
        }

        public void setyVelocity(double yVelocity) {
            this.yVelocity = yVelocity;
        }
    }
}
