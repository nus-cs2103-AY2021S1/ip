import java.time.LocalDate;

/**
 * Class representing a Todo.
 * @author vanGoghhh
 */

public class Todo extends Task {

    /**
     * Constructor for Todo.
     * @param description description of the Todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    protected LocalDate getTaskDeadline() {
        return null;
    }

    /**
     * Method to convert deadline to a file friendly format.
     * @return string representation of file format.
     */
    protected String inputInFile() {
        return "T//" + this.getTaskStatus() + "//"
                + super.getDescription();
    }

    @Override
    protected Task updateTimedTaskDeadline(LocalDate newDueDate) {
        return this;
    }

    /**
     * Updates the task with new description.
     * @param newDescription new task description.
     * @return task with new description.
     */
    @Override
    protected Task updateTaskDescription(String newDescription) {
        Todo newTodo = new Todo(newDescription);
        if (this.getStatus()) {
            newTodo.markAsDone();
        }
        return newTodo;

    }

    /**
     * Prints the Todo object.
     * @return string representation of a Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
