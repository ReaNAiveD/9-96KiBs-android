package com.nine96kibs.nine96kibsandroid.vo;

public class TaskInfoVO {

    private Integer taskId;

    private String taskName;

    private Integer taskReciteTotal;

    private Integer taskReciteCommand;

    private Integer taskReciteLearning;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Integer getTaskReciteTotal() {
        return taskReciteTotal;
    }

    public void setTaskReciteTotal(Integer taskReciteTotal) {
        this.taskReciteTotal = taskReciteTotal;
    }

    public Integer getTaskReciteCommand() {
        return taskReciteCommand;
    }

    public void setTaskReciteCommand(Integer taskReciteCommand) {
        this.taskReciteCommand = taskReciteCommand;
    }

    public Integer getTaskReciteLearning() {
        return taskReciteLearning;
    }

    public void setTaskReciteLearning(Integer taskReciteLearning) {
        this.taskReciteLearning = taskReciteLearning;
    }
}

