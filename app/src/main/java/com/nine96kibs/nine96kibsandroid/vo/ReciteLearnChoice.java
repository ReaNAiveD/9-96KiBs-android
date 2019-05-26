package com.nine96kibs.nine96kibsandroid.vo;

public class ReciteLearnChoice {

    private Integer reciteId;

    private Integer userId;

    /**
     * 0 - 不认识
     * 1 - 有印象
     * 2 - 已掌握
     */
    private Integer choice;

    public Integer getReciteId() {
        return reciteId;
    }

    public void setReciteId(Integer reciteId) {
        this.reciteId = reciteId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getChoice() {
        return choice;
    }

    public void setChoice(Integer choice) {
        this.choice = choice;
    }
}
