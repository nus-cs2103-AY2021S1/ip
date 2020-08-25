package main.java.duke.task;

import main.java.duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public abstract class Task {

    protected static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");
    protected static final DateTimeFormatter SAVE_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    protected static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

    private final String name;
    protected Optional<LocalDate> date;
    private boolean isDone;

    public Task(String name) {
        this.name = name;
        this.isDone = false;
        this.date = Optional.empty();
    }

    /**
     * Return the Name of the Task.
     * @return String representing the name of Task.
     */
    public String getName() {
        return name;
    }

    public void setDate(LocalDate date) {
        this.date = Optional.of(date);
    }

    public Optional<LocalDate> getDate() {
        return this.date;
    }

    public void setDone() throws DukeException {
        if (isDone) {
            throw new DukeException("Task is already done");
        }
        this.isDone = true;
    }

    public String toSaveFormat() {
        String status = isDone ? "1" : "0";
        return " | " + status + " | " + name ;
    }

    @Override
    public String toString() {
        //String icon = isDone ? "\u2713" : "\u2718";
        String icon = isDone ? "X" : " ";
        return "[" + icon + "] " + name;
    }
}
