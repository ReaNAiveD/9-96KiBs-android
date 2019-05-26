package com.nine96kibs.nine96kibsandroid.collect;

public class Collection {

    private int type;
    private int parentPosition;
    private int childPosition;
    private String info;

    Collection(int type, int parentPosition, int childPosition, String info) {
        this.type = type;
        this.parentPosition = parentPosition;
        this.childPosition = childPosition;
        this.info = info;
    }

    int getType() {
        return type;
    }

    int getParentPosition() {
        return parentPosition;
    }

    int getChildPosition() {
        return childPosition;
    }

    String getInfo() {
        return info;
    }

}
