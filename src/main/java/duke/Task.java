package main.java.duke;

public class Task {

    /** Content of the Task object */
    protected String description;

    /** Completion status of the Task object */
    protected boolean isDone;


    /**
     * Constructor of Task class.
     * Initialize class members description, completion status.
     *
     * @param description  Content of the Task object.
     */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }


    /**
     * Another Constructor of Task class.
     * Initialize class members description, completion status.
     *
     * @param description  Content of the Task object.
     * @param isDone  Completion Status of the Task object.
     */
    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }


    /**
     * Return Completion status icon of Task object.
     *
     * @return Tick or cross String to represent completion status of the Task object.
     */
    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }


    /**
     * Mark completion status of Task object as done.
     */
    public void markAsDone() {
        isDone = true;
    }


    /**
     * Return Content of Task object.
     *
     * @return String to represent content of the Task object.
     */
    public String getDescription() {
        return this.description;
    }


    /**
     * Return Type of Task object.
     *
     * @return String to represent type of the Task object.
     */
    public String getType() {
        return "General_Task";
    }


    /**
     * Return Completion status of Task object.
     *
     * @return String "1" or "0" to represent completion status of the Task object.
     */
    public String isDone() {
        return isDone ? "1" : "0";
    }


    /**
     * Return Specific information of Task object.
     *
     * @return Type, status, content, and empty datetime of Task object in the form of an array of Strings.
     */
    public String[] getInfo() {
        return new String[]{this.getType(), this.isDone(), this.description, ""};
    }


    /**
     * Return String representation of Task object.
     *
     * @return String representation of Task object.
     */
    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + description;
    }
}
