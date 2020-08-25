package main.java.duke.task;

import main.java.duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

/**
 * Represents a Deadline Task.
 */
public class Deadline extends Task {
    private LocalTime time;

    /**
     * Create an instance of Deadline.
     * @param name The name of the task.
     * @param taskDetail The timing of the Deadline.
     * @throws DukeException If taskDetail is badly formatted.
     */
    public Deadline(String name, String taskDetail) throws DukeException {
        super(name);
        String[] input = taskDetail.split("\\s+");
        try {
            super.setDate(LocalDate.parse(input[0]));
            if (input.length == 2) {
                time = LocalTime.parse(input[1]);
            } else{
                time = null;
            }
        } catch (DateTimeParseException err) {
            throw new DukeException("Error: Please key in as: \n " +
                    "event [title] /by YYYY-MM-DD HH:MM");
        }
    }

    /**
     * Convert Task into the saved format.
     * @return A String that described the Task in saved format.
     */
    @Override
    public String toSaveFormat() {
        return String.format("D%s | %s %s", super.toSaveFormat(),
                Task.SAVE_DATE_FORMATTER.format(super.date.get()), Task.TIME_FORMATTER.format(time));
    }

    /**
     * Convert Task into the display format.
     * @return A String that described the Task in display format.
     */
    @Override
    public String toString() {
        String dateTime = Task.DATE_FORMATTER.format(super.date.get());
        if (time != null) {
            dateTime += " " + Task.TIME_FORMATTER.format(this.time);
        }
        return String.format("[D]%s (by: %s)", super.toString(), dateTime);
    }
}
