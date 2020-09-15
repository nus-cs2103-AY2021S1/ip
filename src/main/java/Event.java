import java.time.LocalDate;

/**
 * Class to represent an event object.
 * @author vanGoghhh
 */

public class Event extends TimedTask {

    /**
     * Constructor for event class.
     * @param description description of the event.
     * @param dueDate duedate of the event.
     */
    public Event(String description, LocalDate dueDate) {
        super(description, dueDate);
    }

    /**
     * Method to convert deadline to a file friendly format.
     * @return string representation of file format.
     */
    @Override
    protected String inputInFile() {
        return "E//" + this.getTaskStatus() + "//"
                + super.getDescription() + "//" + this.getTaskDeadline();
    }

    /**
     * Updates the task with new date.
     * @param newDueDate new date to update with.
     * @return task with new date.
     */
    @Override
    protected Event updateTimedTaskDeadline(LocalDate newDueDate) {
        Event newEvent = new Event(super.getDescription(), newDueDate);
        if (this.getStatus()) {
            newEvent.markAsDone();
        }
        return newEvent;
    }

    /**
     * Updates the task with new description
     * @param newDescription new task description.
     * @return task with new description.
     */
    @Override
    protected Event updateTaskDescription(String newDescription) {
        Event newEvent = new Event(newDescription, super.getTaskDeadline());
        if (this.getStatus()) {
            newEvent.markAsDone();
        }
        return newEvent;
    }
}