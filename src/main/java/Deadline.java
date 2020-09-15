import java.time.LocalDate;

/**
 * Class to represent a deadline object.
 * @author vanGoghhh
 */

public class Deadline extends TimedTask {

    /**
     * Constructor for a timed task.
     *
     * @param description details of the task.
     * @param dueDate     deadline of the task.
     */
    public Deadline(String description, LocalDate dueDate) {
        super(description, dueDate);
    }

    /**
     * Method to convert deadline to a file friendly format.
     * @return string representation of file format.
     */
    @Override
    protected String inputInFile() {
        return "D//" + this.getTaskStatus() + "//"
                + super.getDescription() + "//" + this.getTaskDeadline();
    }

    /**
     * Updates the task with new date.
     * @param newDueDate new date to update with.
     * @return task with new date.
     */
    @Override
    protected Deadline updateTimedTaskDeadline(LocalDate newDueDate) {
        Deadline newDeadline = new Deadline(super.getDescription(), newDueDate);
        if (this.getStatus()) {
            newDeadline.markAsDone();
        }
        return newDeadline;
    }

    /**
     * Updates the task with new description
     * @param newDescription new task description.
     * @return task with new description.
     */
    @Override
    protected Deadline updateTaskDescription(String newDescription) {
        Deadline newDeadline = new Deadline(newDescription, super.getTaskDeadline());
        if (this.getStatus()) {
            newDeadline.markAsDone();
        }
        return newDeadline;
    }
}