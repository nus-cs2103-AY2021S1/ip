import java.time.LocalDate;
import java.util.Optional;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    public Optional<LocalDate> getDate() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "T|" + super.toString();
    }
}
