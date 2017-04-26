package com.github.appreciated.circularprogressbar;

import com.vaadin.annotations.JavaScript;
import com.vaadin.ui.AbstractJavaScriptComponent;

/**
 * Created by Johannes on 10.01.2017.
 */
@JavaScript({"vaadin://components/circular-progressbar/circularprogressbar_connector.js"})
public class CircularProgressBar extends AbstractJavaScriptComponent {
    public CircularProgressBar() {
        if (getHeight() == -1.0F && getWidth() == -1.0F) {
            setWidth(100, Unit.PIXELS);
            setHeight(100, Unit.PIXELS);
        }
    }

    public void setValue(float value) {
        getState().value = value;
    }

    public float getProgress() {
        return getState().value;
    }

    public void setScale(float value) {
        getState().scale = value;
    }

    public float getScale() {
        return getState().scale;
    }

    public void setLabel(String value) {
        getState().label = value;
    }

    public String getLabel() {
        return getState().label;
    }

    @Override
    protected CircularProgressBarState getState() {
        return (CircularProgressBarState) super.getState();
    }

}
