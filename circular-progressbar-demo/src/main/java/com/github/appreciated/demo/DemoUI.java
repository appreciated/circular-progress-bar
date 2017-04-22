package com.github.appreciated.demo;

import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

@Theme("demo")
@Title("CircularProgressbar Add-on Demo")
@Push
@SuppressWarnings("serial")
public class DemoUI extends UI {


    private TestView component;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        // Initialize our new UI component
        component = new TestView();
        // Show it in the middle of the screen
        final VerticalLayout layout = new VerticalLayout();
        layout.setStyleName("demoContentLayout");
        layout.setSizeFull();
        layout.setMargin(false);
        layout.setSpacing(false);
        layout.addComponent(component);
        layout.setComponentAlignment(component, Alignment.MIDDLE_CENTER);
        setContent(layout);
    }

    @Override
    public void attach() {
        super.attach();
        new Thread(() -> {
            for (int i = 0; i <= 10; i++) {
                final int progress = i * 10;
                component.getProgressBars().forEach(circularProgressBar -> {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DemoUI.this.access(() -> circularProgressBar.setProgress(progress));
                });
            }
            for (int i = 10; i >= 0; i--) {
                final int progress = i * 10;
                component.getProgressBars().forEach(circularProgressBar -> {
                    try {
                        Thread.sleep(250);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    DemoUI.this.access(() -> circularProgressBar.setProgress(progress));
                });
            }
        }).start();
    }


}
