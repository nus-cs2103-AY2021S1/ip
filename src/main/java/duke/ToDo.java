package duke;

import java.time.LocalDateTime;
import java.util.Optional;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public Optional<LocalDateTime> getLocalDateTime() {
        return Optional.empty();
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]"
                + "[" + getStatusIcon() + "]"
                + description;
    }
}
