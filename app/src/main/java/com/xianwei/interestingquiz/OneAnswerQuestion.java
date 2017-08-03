package com.xianwei.interestingquiz;

import android.graphics.Bitmap;

/**
 * Created by xianwei li on 8/3/2017.
 */

public class OneAnswerQuestion {

    private String question;
    private Bitmap image;
    private String answerA;
    private String answerB;
    private String answerC;
    private String hint;

    public OneAnswerQuestion(String question,
                             Bitmap image,
                             String answerA,
                             String answerB,
                             String answerC,
                             String hint) {
        this.question = question;
        this.image = image;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.hint = hint;
    }

    public String getQuestion() {
        return question;
    }

    public Bitmap getImage() {
        return image;
    }

    public String getAnswerA() {
        return answerA;
    }

    public String getAnswerB() {
        return answerB;
    }

    public String getAnswerC() {
        return answerC;
    }

    public String getHint() {
        return hint;
    }
}
