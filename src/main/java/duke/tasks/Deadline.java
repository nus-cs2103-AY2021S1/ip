package duke.tasks;

/**
 * Deadline is a type of Task.
 */

public class Deadline extends Task {

    private String dueDate;

    /**
     * Construct a new Deadline.
     *
     * @param name    Name or description of the deadline.
     * @param dueDate The due date of the deadline.
     */
    public Deadline(String name, String dueDate) {
        super(name);
        this.dueDate = dueDate;
    }
    /**
     * Gets the type of Deadline.
     * @return "D" which represents deadline
     */
    @Override
    public String getType() {
        return "D";
    }

    /**
     * Gets the due date of the deadline.
     * @return The due date of the deadline
     */
    @Override
    public String getTime() {
        return dueDate;
    }

    /**
     * Convert the information of an event into array.
     *
     * @return A array of String.
     */
    public String[] toArray(){
        String status;
        if(this.isDone){
            status = "1";
        }else{
            status = "0";
        }
        String[] result = new String[]{"D", status, this.name, dueDate};
        return result;
    }

    /**
     * Override the toString method.
     *
     * @return the description and due date of the deadline.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + dueDate + ")";
    }
}
