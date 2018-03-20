package acm.event.codetocreate18.Model.Data;



public class QuizQuestionModel {
    public String statement;
    public String[] choices;
    public int correctChoice;

    public QuizQuestionModel(String statement, String choices[], int correctChoice) {
        this.statement = statement;
        this.choices = choices;
        this.correctChoice = correctChoice;
    }

}
