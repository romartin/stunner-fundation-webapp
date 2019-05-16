package org.roger600.stunner.fundation.client;

import java.util.Collection;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

import com.google.gwt.animation.client.Animation;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.ui.RootPanel;
import org.jboss.errai.common.client.api.Caller;
import org.jboss.errai.ioc.client.api.EntryPoint;
import org.jboss.errai.ioc.client.container.SyncBeanDef;
import org.jboss.errai.ioc.client.container.SyncBeanManager;
import org.jboss.errai.security.shared.api.identity.User;
import org.jboss.errai.security.shared.service.AuthenticationService;
import org.uberfire.client.mvp.ActivityManager;
import org.uberfire.client.mvp.PerspectiveActivity;
import org.uberfire.client.mvp.PlaceManager;
import org.uberfire.client.views.pfly.PatternFlyEntryPoint;
import org.uberfire.client.views.pfly.modal.ErrorPopupView;
import org.uberfire.client.views.pfly.sys.PatternFlyBootstrapper;
import org.uberfire.client.workbench.events.ApplicationReadyEvent;
import org.uberfire.client.workbench.widgets.menu.WorkbenchMenuBar;
import org.uberfire.ext.plugin.client.perspective.editor.layout.editor.PerspectiveEditorSettings;
import org.uberfire.workbench.model.menu.Menus;

import static org.uberfire.workbench.model.menu.MenuFactory.newTopLevelMenu;

/**
 * GWT's Entry-point
 */
@EntryPoint
public class ShowcaseEntryPoint {

    @Inject
    private SyncBeanManager manager;
    @Inject
    private WorkbenchMenuBar menubar;
    @Inject
    private User user;
    @Inject
    private PlaceManager placeManager;
    @Inject
    private ActivityManager activityManager;
    @Inject
    private Caller<AuthenticationService> authService;
    @Inject
    private ErrorPopupView errorPopupView;
    @Inject
    private PatternFlyEntryPoint pflyEntryPoint;
    @Inject
    private PerspectiveEditorSettings perspectiveEditorSettings;

    @PostConstruct
    public void startApp() {
        PatternFlyBootstrapper.ensureMomentIsAvailable();
        PatternFlyBootstrapper.ensureBootstrapDateRangePickerIsAvailable();
        perspectiveEditorSettings.setTagsEnabled(true);
        hideLoadingPopup();
    }

    private void onApplicationReady(@Observes final ApplicationReadyEvent event) {
        setupMenu();
    }

    private void setupMenu() {
        final PerspectiveActivity defaultPerspective = getDefaultPerspectiveActivity();
        final Menus menus =
                newTopLevelMenu("Home")
                        .perspective(defaultPerspective.getIdentifier())
                        .endMenu()
                        .build();
        menubar.clear();
        menubar.addMenus(menus);
    }

    private PerspectiveActivity getDefaultPerspectiveActivity() {
        PerspectiveActivity defaultPerspective = null;
        final Collection<SyncBeanDef<PerspectiveActivity>> perspectives = manager.lookupBeans(PerspectiveActivity.class);
        final Iterator<SyncBeanDef<PerspectiveActivity>> perspectivesIterator = perspectives.iterator();

        while (perspectivesIterator.hasNext()) {
            final SyncBeanDef<PerspectiveActivity> perspective = perspectivesIterator.next();
            final PerspectiveActivity instance = perspective.getInstance();
            if (instance.isDefault()) {
                defaultPerspective = instance;
                break;
            } else {
                manager.destroyBean(instance);
            }
        }
        return defaultPerspective;
    }

    private void hideLoadingPopup() {
        final Element e = RootPanel.get("loading").getElement();

        new Animation() {

            @Override
            protected void onUpdate(double progress) {
                e.getStyle().setOpacity(1.0 - progress);
            }

            @Override
            protected void onComplete() {
                e.getStyle().setVisibility(Style.Visibility.HIDDEN);
            }
        }.run(500);
    }
}
