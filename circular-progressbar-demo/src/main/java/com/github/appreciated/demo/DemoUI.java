package com.github.appreciated.demo;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Component;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

@Theme("demo")
@Title("CircularProgressbar Add-on Demo")
@Push
@SuppressWarnings("serial")
public class DemoUI extends UI {

    private VerticalLayout component;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        StylingView view = new StylingView();
        view.setMargin(false);
        component = new VerticalLayout();
        component.addComponent(view);
        component.setComponentAlignment(view, Alignment.MIDDLE_CENTER);
        component.setWidth("300px");
        component.setHeight("300px");
        component.setMargin(false);
        component.setStyleName("demoContentLayout");
        final VerticalLayout layout = new VerticalLayout();
        layout.setSizeFull();
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.addComponent(component);
        layout.setComponentAlignment(component, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }
/*
    @Override
    public void attach() {
        super.attach();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                final double value = i * 10 + 0.3253452345234523452345;
                component.getProgressBars().forEach(circularProgressBar -> {
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
                final double value = i * 10 + 0.3253452345234523452345;
                component.getProgressBars().forEach(circularProgressBar -> {
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
    }*/
}
