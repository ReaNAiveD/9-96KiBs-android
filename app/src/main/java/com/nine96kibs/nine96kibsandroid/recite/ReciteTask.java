package com.nine96kibs.nine96kibsandroid.recite;

public class ReciteTask {

    private int id;
    private String name;
    private int done;
    private int half;
    private int todo;

    public ReciteTask(int id, String name, int done, int half, int todo) {
        this.id = id;
        this.name = name;
        this.done = done;
        this.half = half;
        this.todo = todo;
    }

    int getId() {
        return id;
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
