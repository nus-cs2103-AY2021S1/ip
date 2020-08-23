/**
 * The deadline is a subclass of Task and it is used to describe tasks that has to be completed by a specific day.
 */
public class deadline extends Task {
    private String day;

    /**
     *
     * @param name super(name) so that it does whatever is mentioned in the parent class
     * @param day assigns this.day to day value
     */
    public deadline(String name, String day) {
        super(name);
        this.day = day;
    }

    /**
     * takes no arguments and overrides the toString method
     * @return the specific representation for deadline class as mentioned with [D] indicating that it is a deadline class
     * and also mentions the deadline.
     */
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.day + ")";
    }
    public String inputListFormat(){
        return "D" + super.inputListFormat() + " | " + this.day;
    }
}