package com.gaponec.objects;

import lombok.Data;

import java.util.Date;

@Data
public class Statistics {
    private int rightAnswers;
    private int wrongAnswers;
    private int unsolved;
    private Date date;

    public Statistics(){
        rightAnswers=0;
        wrongAnswers=0;
        unsolved=0;
        date = new Date();
    }

    public double getPercentOfRightAnswers(){
        double percent = rightAnswers / (wrongAnswers + rightAnswers + unsolved);

        return percent;
    }

    public void incrementRight(){
        rightAnswers++;
    }

    public void incrementWrong(){
        wrongAnswers++;
    }

    public void decrementUnsolved(){
        unsolved--;
    }

}
