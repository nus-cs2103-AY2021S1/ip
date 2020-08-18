public class Deadline extends Task{

    public static String icon = "D";
    private String preposition;
    private String deadlineString;


    Deadline(String taskString, String preposition, String deadlineString) {
        super(taskString);
        this.preposition = preposition;
        this.deadlineString = deadlineString;
    }

    @Override
    public String toString() {
        String statusIcon = (status)?"✓":"✗";
        return "[" + icon + "]" + "[" + statusIcon + "] "
                + this.taskString + " (" + this.preposition + ": " + this.deadlineString + ")";
    }
}
