package com.xianwei.interestingquiz;

/**
 * Created by xianwei li on 8/4/2017.
 */

public class InputAnswerQuestion {
    private String question;
    private String editHint;
    private String buttonHint;

    public InputAnswerQuestion(String question, String editHint, String buttonHint) {
        this.question = question;
        this.editHint = editHint;
        this.buttonHint = buttonHint;
    }

    public String getQuestion() {
        return question;
    }

    public String getEditHint() {
        return editHint;
    }

    public String getButtonHint() {
        return buttonHint;
    }
}
