package com.nine96kibs.nine96kibsandroid.remember;

public class RememberText {

    private String body;
    private String source;

    public RememberText(String body, String source) {
        this.body = body;
        this.source = source;
    }

    String getBody() {
        return body;
    }

    String getSource() {
        return source;
    }

}
