/**
 * Deadlines are tasks that have a deadline.
 */

public class Deadlines extends Task{
    private String deadline;
    String TASKINDICATOR = "[D]";

    public Deadlines(String userInput, String deadline) {
        super(userInput);
        this.deadline = deadline;
    }

    public String printName() {
        return TASKINDICATOR + super.printName() + "(by: " + this.deadline + ")";
    }
}