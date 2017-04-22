package com.github.appreciated.demo;

import com.github.appreciated.circular_progressbar.CircularProgressBar;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Johannes on 22.04.2017.
 */
public class TestView extends TestDesign {

    public List<CircularProgressBar> getProgressBars() {
        return Arrays.asList(progress1, progress2, progress3, progress4);
    }
}
