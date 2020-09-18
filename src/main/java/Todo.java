import java.time.LocalDate;

/**
 * Encapsulates a Todo object.
 */
public class Todo extends Task {

    public Todo(String description) {
        super(description);
    }

    @Override
    public LocalDate getDate() {
        return LocalDate.MIN;
    }

    /**
     * Saves whether the task is done or not into storage.
     * @return a string representation of the task.
     */
    @Override
    public String saveTask() {
        return "T" + super.saveTask();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}