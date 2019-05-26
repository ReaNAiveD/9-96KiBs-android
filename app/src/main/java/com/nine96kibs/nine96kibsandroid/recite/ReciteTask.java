package com.nine96kibs.nine96kibsandroid.recite;

public class ReciteTask {

    private String name;
    private int done;
    private int half;
    private int todo;

    public ReciteTask(String name, int done, int half, int todo) {
        this.name = name;
        this.done = done;
        this.half = half;
        this.todo = todo;
    }

    String getName() {
        return name;
    }

    int getDone() {
        return done;
    }

    int getHalf() {
        return half;
    }

    int getTodo() {
        return todo;
    }

}
