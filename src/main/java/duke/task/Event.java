package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

import duke.exception.DukeException;

/**
 * Represents a type of Task that has a specific date and time of occurrence.
 * Similar to Deadline Class.
 */
public class Event extends Task {
    protected String at;
    protected LocalDate date;
    protected LocalTime time;

    /**
     * Represents a Event task.
     * @param description information for the task itself
     * @param at information for the time and date of occurrence
     */
    public Event(String description, String at) throws DukeException {
        super(description);
        this.update(at);
    }

    private String format(String input) {
        String[] component = input.split("/");
        if (component[0].length() == 1) {
            component[0] = "0" + component[0];
        }
        return component[2] + "-" + component[1] + "-" + component[0];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter
                .ofPattern("MMM d yyyy")) + " " + time + ")";
    }

    @Override
    public String getDateAsString() {
        return this.at.split(" ")[0];
    }

    @Override
    public String convertTxt() {
        return "E | " + (this.status ? "1" : "0") + " | " + name + " | " + at;
    }

    @Override
    public Optional<LocalDate> getDate() {
        return Optional.ofNullable(this.date);
    }

    @Override
    public Optional<LocalTime> getTime() {
        return Optional.ofNullable(this.time);
    }

    @Override
    public void update(String time) throws DukeException {
        try {
            this.at = time.trim();
            String[] timeComponent = at.split(" ");
            this.date = LocalDate.parse(format(timeComponent[0].trim()));
            String hour = timeComponent[1].substring(0, 2);
            String minute = timeComponent[1].substring(2);
            this.time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));

        } catch (Exception e) {
            throw new DukeException("Invalid date and time format detected!");
        }
    }

}
