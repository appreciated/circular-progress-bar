package com.github.appreciated.demo;

import com.github.appreciated.circular_progressbar.CircularProgressBar;
import com.vaadin.annotations.Push;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

import javax.servlet.annotation.WebServlet;

@Theme("demo")
@Title("CircularProgressbar Add-on Demo")
@Push
@SuppressWarnings("serial")
public class DemoUI extends UI {


    private Holder component;

    @WebServlet(value = "/*", asyncSupported = true)
    @VaadinServletConfiguration(productionMode = false, ui = DemoUI.class)
    public static class Servlet extends VaadinServlet {
    }

    @Override
    protected void init(VaadinRequest request) {
        // Initialize our new UI component
        component = new Holder(new CircularProgressBar());
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

    private class Holder extends HorizontalLayout {

        private CircularProgressBar component;

        Holder(CircularProgressBar component) {
            this.component = component;
            this.addComponent(component);
        }

        @Override
        public void attach() {
            super.attach();
            new Thread(() -> {
                try {
                    Thread.sleep(5000);
                    for (int i = 0; i <= 10; i++) {
                        Thread.sleep(1000);
                        final int progress = i * 10;
                        DemoUI.this.access(() -> {
                            this.component.setProgress(progress);
                        });
                    }
                    for (int i = 10; i >= 0; i--) {
                        Thread.sleep(1000);
                        final int progress = i * 10;
                        DemoUI.this.access(() -> {
                            this.component.setProgress(progress);
                        });
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
