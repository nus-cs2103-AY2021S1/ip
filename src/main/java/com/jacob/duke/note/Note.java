package main.java.com.jacob.duke.note;

public class Note {
    //fields
    private String question;
    private String answer;
    private String type;

    /**
     * Constructor for Note with questions and answers.
     *
     * @param question Description of the question being asked.
     * @param answer Description of the answer to the question.
     */
    public Note(String question, String answer) {
        this.question = question;
        this.answer = answer;
        this.type = "N";
    }

    public String convertToFileFormat() {
        return String.format("%s~%s~%s", this.type, this.question, this.answer);
    }

    public String getCurrentStatus() {
        return "Question is: " + this.question + "\n    Answer is: " + this.answer;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
