import java.util.Optional;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    String getStringType() {
        return "T";
    }

    @Override
    Optional<String> getDate() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
