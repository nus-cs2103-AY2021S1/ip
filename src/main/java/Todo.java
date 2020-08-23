import java.time.LocalDate;
import java.util.Optional;

/**
 *  Represents a to-do task with only a description.
 */
public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    /**
     * Returns an empty Optional since a to-do task does not have a date.
     * @return an emptu Optional.
     */
    public Optional<LocalDate> getDate() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "T|" + super.toString();
    }
}
