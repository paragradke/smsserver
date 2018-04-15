package com.example.authproject.cache;

public class SLAEntry {

    public SLAEntry(final String from) {
        this.counter = 0;
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        this.counter++;
    }

    private String from;
    private int counter;
}
