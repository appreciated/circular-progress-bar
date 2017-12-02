package com.github.appreciated.demo;

import com.github.appreciated.circularprogressbar.CircularProgressBar;
import com.vaadin.server.ThemeResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import java.util.List;

public class MainView extends VerticalLayout {

    private final VerticalLayout component;
    private final TestView testView;
    private List<CircularProgressBar> progressbars;
    private UI ui;

    MainView() {
        StylingView view = new StylingView();
        view.setMargin(false);
        view.circularProgress.setScale(0.75f);
        view.circularProgress.setLabel("Loading");
        view.circularProgress.setImage(new Image("image", new ThemeResource("images/test.png")));
        view.circularProgress.setValue(0.5f);
        view.addStyleName("rotate");
        component = new VerticalLayout();
        component.addComponent(view);
        component.setComponentAlignment(view, Alignment.MIDDLE_CENTER);
        component.setWidth("350px");
        component.setHeight("350px");
        component.setMargin(false);
        component.setStyleName("demoContentLayout");
        testView = new TestView();
        progressbars = testView.getProgressBars();
        setMargin(true);
        setSpacing(false);
        addComponent(component);
        addComponent(testView);
        setComponentAlignment(component, Alignment.MIDDLE_CENTER);
    }

    @Override
    public void attach() {

        super.attach();
        ui = getUI();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                final float value = i / 10.0f;
                progressbars.forEach(circularProgressBar -> {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ui.access(() -> circularProgressBar.setValue(value));
                    System.out.println(value);
                });
            }
            for (int i = 10; i >= 0; i--) {
                final float value = i / 10.0f;
                progressbars.forEach(circularProgressBar -> {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    ui.access(() -> circularProgressBar.setValue(value));
                    System.out.println(value);
                });
            }
        }).start();
    }
}
