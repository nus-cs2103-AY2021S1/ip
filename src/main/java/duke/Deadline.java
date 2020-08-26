package main.java.duke;

public class Deadline extends Task {

    /** date and time of a Deadline object */
    protected String by;


    /**
     * Constructor of Deadline.
     *
     * @param description  Content of the Task.
     * @param by  Date and time of Deadline object.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }


    /**
     * Another constructor of Deadline.
     *
     * @param description  Content of the Task.
     * @param by  Date and time of Deadline object.
     * @param isDone  Completion status of the Deadline object
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }


    /**
     * Returns First letter of Deadline to represent its type.
     *
     * @return "D" to represent type of Deadline.
     */
    @Override
    public String getType() {
        return "D";
    }


    /**
     * Returns Date and time of the Deadline object.
     *
     * @return Date and time of Deadline object.
     */
    public String getBy() {
        return this.by;
    }


    /**
     * Returns Information about the Deadline object.
     *
     * @return Type, isDone, description, and date and time.
     */
    @Override
    public String[] getInfo() {
        return new String[]{this.getType(), this.isDone(), this.description, this.getBy()};
    }


    /**
     * Returns String representation of the Deadline object.
     *
     * @return The string the object prints out to be.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}