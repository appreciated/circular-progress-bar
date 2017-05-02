package com.github.appreciated.circularprogressbar;

import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Image;

/**
 * Created by Johannes on 01.05.2017.
 */
public class CircularProgressBar extends AbsoluteLayout {
    private final CircularProgressBarClient progressbar;
    private Image image;

    public CircularProgressBar() {
        progressbar = new CircularProgressBarClient();
        this.addComponent(progressbar);
        if (getHeight() == -1.0F && getWidth() == -1.0F) {
            setWidth(100, Unit.PIXELS);
            setHeight(100, Unit.PIXELS);
        }
        this.setPosition(progressbar, getComponentPosition(1));
    }

    public CircularProgressBar withImage(Image image) {
        setImage(image);
        return this;
    }

    public void setImage(Image image) {
        if (image != null) {
            this.removeComponent(image);
        }
        this.image = image;
        image.setSizeFull();
        this.addComponent(image);
        this.setPosition(image, getComponentPosition(0));
    }

    public void setValue(float value) {
        progressbar.getState().value = value;
    }

    public float getProgress() {
        return progressbar.getState().value;
    }

    public void setScale(float value) {
        progressbar.getState().scale = value;
    }

    public float getScale() {
        return progressbar.getState().scale;
    }

    public void setLabel(String value) {
        progressbar.getState().label = value;
    }

    public String getLabel() {
        return progressbar.getState().label;
    }

    public CircularProgressBarClient getProgressbar() {
        return progressbar;
    }

    private ComponentPosition getComponentPosition(int zIndex) {
        ComponentPosition componentPosition = new ComponentPosition();
        componentPosition.setTop(0.0f, Unit.PIXELS);
        componentPosition.setBottom(0.0f, Unit.PIXELS);
        componentPosition.setLeft(0.0f, Unit.PIXELS);
        componentPosition.setRight(0.0f, Unit.PIXELS);
        componentPosition.setZIndex(zIndex);
        return componentPosition;
    }

}
