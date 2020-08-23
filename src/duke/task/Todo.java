package duke.task;

import java.time.LocalDate;

/**
 * Represents a Todo which is a Task which needs to be done.
 */
public class Todo extends Task{

    /**
     * Creates a Todo instance containing a description
     *
     * @param description Description of Todo to be done.
     */
    public Todo(String description) {
        this(description,false);
    }

    /**
     * Creates a Todo instance containing a description
     *
     * @param description Description of Todo to be done.
     * @param isDone True if the Todo is already done otherwise false.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    /**
     * Format in which the Todo should be saved in Storage.
     *
     * @return String format of the Todo for saving in Storage.
     */
    @Override
    public String saveFormat() {
        return "T" + "~" + super.saveFormat();
    }

    /**
     * Checks whether the Todo has the same Date as specified.
     *
     * @param theDate Date to check whether the Todo has occurred on.
     * @return False since a Todo has no Date.
     */
    @Override
    public boolean hasSameDate(LocalDate theDate) {
        return false;
    }

    /**
     * Returns the String representation of the Todo to be displayed to the user.
     *
     * @return String representation of Todo.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
