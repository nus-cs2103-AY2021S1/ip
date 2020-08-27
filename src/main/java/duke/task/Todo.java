package duke.task;

import java.util.Optional;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String getStringType() {
        return "T";
    }

    @Override
    public Optional<String> getDate() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
