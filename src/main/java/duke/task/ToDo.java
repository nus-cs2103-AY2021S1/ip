package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

/**
 * Represents probably the simplest type of Task.
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String convertTxt() {
        return "T | " + (this.status ? "1" : "0") + " | " + name;
    }

    @Override
    public String getDateAsString() {
        return "";
    }

    @Override
    public Optional<LocalDate> getDate() {
        return Optional.empty();
    }

    @Override
    public Optional<LocalTime> getTime() {
        return Optional.empty();
    }

}
