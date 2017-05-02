package com.github.appreciated.demo;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Image;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

@Theme("demo")
@Title("CircularProgressbar Add-on Demo")
@Push
@SuppressWarnings("serial")
public class DemoUI extends UI {

    private VerticalLayout component;
    private TestView testView;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
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
        final VerticalLayout layout = new VerticalLayout();
        layout.setMargin(true);
        layout.setSpacing(false);
        layout.addComponent(component);
        layout.addComponent(testView);
        layout.setComponentAlignment(component, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    @Override
    public void attach() {
        super.attach();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                final float value = i / 10.0f;
                testView.getProgressBars().forEach(circularProgressBar -> {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DemoUI.this.access(() -> circularProgressBar.setValue(value));
                    System.out.println(value);
                });
            }
            for (int i = 10; i >= 0; i--) {
                final float value = i / 10.0f;
                testView.getProgressBars().forEach(circularProgressBar -> {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DemoUI.this.access(() -> circularProgressBar.setValue(value));
                    System.out.println(value);
                });
            }
        }).start();
    }
}
