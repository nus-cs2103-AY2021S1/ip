package data.task;

import data.exception.DukeInvalidUserInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A specific type of task that contains a description of a task and a specific date and time.
 */
public class Deadline extends Task {
    private LocalDateTime dateTime;
    private String dateTimeTxt;

    public Deadline(String description, String dateTime) throws DukeInvalidUserInputException {
        super(description);
        convertDateTime(dateTime);
    }

    /**
     * Converts the given string into a LocalDateTime to be stored in the given Deadline.
     * @param dateTime to be converted into LocalDateTime.
     * @throws DukeInvalidUserInputException when an invalid a date time format is used as input.
     */
    private void convertDateTime(String dateTime) throws DukeInvalidUserInputException {
        try {
            String[] dateTimeArr = dateTime.split(" ");
            this.dateTime = LocalDateTime.parse(dateTimeArr[0] + "T" + dateTimeArr[1].substring(0, 2) +
                    ":" + dateTimeArr[1].substring(2, 4));
            this.dateTimeTxt = this.dateTime.format(DateTimeFormatter.ofPattern("d MMMM yyyy hh:mm a"));
        } catch (DateTimeParseException e) {
            throw new DukeInvalidUserInputException("It seems you have entered an invalid date and time. " +
                    "The format should be as follows YYYY-MM-DD hhmm.");
        }
    }

    /**
     * Converts the Deadline task into a string to be saved into a text file.
     * @return Deadline task in the form of a string.
     */
    public String toTxtFormat() {
        return "D | " + super.toTxtFormat() + " | " + this.dateTimeTxt;
    }

    /**
     * Parses a given string array into a Deadline task.
     * @param txtArray to be parsed into a Deadline task.
     * @return Deadline task based on input string array.
     * @throws DukeInvalidUserInputException when an invalid date and time format is found in the input string array.
     */
    public static Deadline parse(String[] txtArray) throws DukeInvalidUserInputException {
        String dateTime = LocalDateTime.parse(txtArray[3].trim(),
                DateTimeFormatter.ofPattern("d MMMM yyyy hh:mm a")).toString().replace('T', ' ');
        dateTime = dateTime.substring(0, dateTime.indexOf(':')) + dateTime.substring(dateTime.indexOf(':') + 1);
        Deadline deadline = new Deadline(txtArray[2].trim(),dateTime);
        if (txtArray[1].trim().equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTimeTxt + ")";
    }
}
