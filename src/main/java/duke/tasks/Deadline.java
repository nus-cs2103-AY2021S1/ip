package duke.tasks;

/**
 * The deadline is a subclass of Task and it is used to describe tasks that has to be completed by a specific day.
 */
public class Deadline extends Task {
    private String dayAndOrTime; //the day and or time that Task deadline had to be completed by
    /**
     * Assigns the name and day values, initializes Deadline task
     *
     * @param name super(name) so that it does whatever is mentioned in the parent class
     * @param day assigns this.day to day value
     */
    public Deadline(String name, String day) {
        super(name);
        this.dayAndOrTime = day;
    }

    /**
     * Assigns the name, done and day values, initializes Deadline task
     *
     * @param name argument in super class constructor
     * @param done argument in super class constructor
     * @param dayAndOrTime assigns to this.day
     */
    public Deadline(String name, boolean done, String dayAndOrTime) {
        super(name, done);
        this.dayAndOrTime = dayAndOrTime;
    }

    /**
     * Takes no arguments and overrides the toString method
     *
     * @return the specific representation for deadline class as mentioned with [D]
     * indicating that it is a deadline class
     * and also mentions the deadline.
     */
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.dayAndOrTime + ")";
    }

    /**
     * Gives a specific string representation for that in the tasks.txt file and overrides that in Task to make
     * it unique to that for Deadline
     *
     * @return the string representation
     */
    public String inputListFormat() {
        return "D" + super.inputListFormat() + " | " + this.dayAndOrTime; //format of Tasks to appear in file in Storage
    }

}
