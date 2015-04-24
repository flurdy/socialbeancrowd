package com.flurdy.socialbeancrowd.io;

import java.util.ArrayList;
import java.util.List;

public class MockOutputter implements SocialOutputter {

    public final List<String> history;

    public MockOutputter(List<String> history) {
        this.history = history;
    }
    public MockOutputter() {
        this.history = new ArrayList<>();
    }

    @Override
    public void print(String inline) {
    }

    @Override
    public void printLine(String line) {
        history.add(line);
    }

    @Override
    public void printLines(List<String> lines) {
        history.addAll(lines);
    }


}
