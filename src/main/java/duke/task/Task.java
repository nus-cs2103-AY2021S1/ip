package duke.task;

import java.time.LocalDate;
import java.util.Optional;

public class Task {
    private static final String DONE = "[x]";
    private static final String NOT_DONE = "[ ]";

    private final String name;
    private String type;
    private boolean isDone;
    private Optional<LocalDate> date;

    public Task(String name, String type) {
        this.name = name;
        this.isDone = false;
        this.type = type;
        this.date = Optional.empty();
    }

    public Task(String name, String type, LocalDate date) {
        this.name = name;
        this.isDone = false;
        this.type = type;
        this.date = Optional.of(date);
    }

    private String getStatus() {
        return this.isDone ? Task.DONE : Task.NOT_DONE;
    }

    public Optional<LocalDate> getDate() {
        return this.date;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public String toString() {
        return String.format("%s%s %s", this.type, this.getStatus(), this.name);
    }
}
