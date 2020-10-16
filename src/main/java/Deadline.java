/**
 * Deadline class represents a Task that is a deadline.
 * Extends from TimedTask class.
 */
public class Deadline extends TimedTask {

    /**
     * Constructor that creates an Deadline object
     * @param name the name of the Deadline.
     * @param dateTime the date amd time of the Deadline in DD-MM-YYYY HHMM format.
     */
    Deadline(String name, String dateTime) {
        super(name, dateTime);
        taskType = "D";
    }

    /**
     * Overloaded constructor that creates an Deadline object with a specified
     * completion status.
     * @param name the name of the Deadline.
     * @param isDone the completion status of the Deadline.
     * @param dateTime the date and time of the Deadline.
     */
    Deadline(String name, Boolean isDone, String dateTime) {
        super(name, isDone, dateTime);
        taskType = "D";
    }
    
    @Override
    public String toString() {
        return String.format("[%s]%s", taskType, super.toString());
    }
}
