/**
 * A subclass of Task.
 * Contains a task description and a time. 
 */
public class Deadline extends Task{
    protected String by;

    //Constructors

    /**
     * Constructor of Deadline object.
     * @param description
     * @param by
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Overloaded constructor of Deadline object.
     * @param description
     * @param isDone
     * @param by
     */
    public Deadline(String description, boolean isDone, String by) {
        super(description, isDone);
        this.by = by;
    }

    /**
     * Marks the deadline task as done.
     * @return new Deadline object with true for isDone.
     */
    @Override
    public Deadline markAsDone() {
        return new Deadline(this.description, true, this.by);
    }

    /**
     * Turns task object into a string to be saved in data file. 
     * @return string in the format of data in data file.
     */
    @Override
    public String stringify() {
        String number = isDone ? "1" : "0";
        return "D | " + number + " | " + super.description + " | " + this.by;
    }

    /**
     * Prints object.
     * @return string of object.
     */
    @Override
    public String toString() {
        return "[D]" + "[" + super.getStatusIcon() + "] " + super.toString() + " (by: " + by + ")";
    }
}
