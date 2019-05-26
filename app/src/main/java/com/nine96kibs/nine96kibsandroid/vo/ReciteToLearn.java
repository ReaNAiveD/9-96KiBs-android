package com.nine96kibs.nine96kibsandroid.vo;

import java.util.Date;

public class ReciteToLearn {
    private Integer reciteId;

    private Integer ifCommand;

    private Date latestLearn;

    private Integer impressionCount;

    private Integer latestChoice;

    private String topic;

    private String topicSub;

    private String answer;

    private String answerSub;

    private Integer articleId;

    private double recitePrior;

    public Integer getReciteId() {
        return reciteId;
    }

    public void setReciteId(Integer reciteId) {
        this.reciteId = reciteId;
    }

    public Integer getIfCommand() {
        return ifCommand;
    }

    public void setIfCommand(Integer ifCommand) {
        this.ifCommand = ifCommand;
    }

    public Date getLatestLearn() {
        return latestLearn;
    }

    public void setLatestLearn(Date latestLearn) {
        this.latestLearn = latestLearn;
    }

    public Integer getImpressionCount() {
        return impressionCount;
    }

    public void setImpressionCount(Integer impressionCount) {
        this.impressionCount = impressionCount;
    }

    public Integer getLatestChoice() {
        return latestChoice;
    }

    public void setLatestChoice(Integer latestChoice) {
        this.latestChoice = latestChoice;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getTopicSub() {
        return topicSub;
    }

    public void setTopicSub(String topicSub) {
        this.topicSub = topicSub;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getAnswerSub() {
        return answerSub;
    }

    public void setAnswerSub(String answerSub) {
        this.answerSub = answerSub;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public double getRecitePrior() {
        return recitePrior;
    }

    public void setRecitePrior(double recitePrior) {
        this.recitePrior = recitePrior;
    }
}