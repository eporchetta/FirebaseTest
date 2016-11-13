package com.example.ernest.firebasetest;

/**
 * Created by Ernest on 11/12/2016.
 */

public class OnlineExamQuestions {
    private String questIndex;
    private String ask;
    private String choiceA;
    private String choiceB;
    private String choiceC;
    private String choiceD;
    private String answer;


    public OnlineExamQuestions(){
        questIndex="";
        ask="";
        choiceA="";
        choiceB="";
        choiceC="";
        choiceD="";
        answer="";
    }

    public OnlineExamQuestions( String  questIndexIn,
                                String  askIn,
                                String  choiceAIn,
                                String  choiceBIn,
                                String  choiceCIn,
                                String  choiceDIn,
                                String  answerIn ){

        questIndex  =questIndexIn;
        ask         =askIn;
        choiceA     =choiceAIn;
        choiceB     =choiceBIn;
        choiceC     =choiceCIn;
        choiceD     =choiceDIn;
        answer      =answerIn;
    }

    public String getQuestIndex(){
        return questIndex;
    }

    public String getAsk(){
        return ask;
    }

    public String getChoiceA(){
        return choiceA;
    }

    public String getChoiceB(){
        return choiceB;
    }

    public String getChoiceC(){
        return choiceC;
    }

    public String getChoiceD(){
        return choiceD;
    }

    public String getAnswer(){
        return answer;
    }

}
