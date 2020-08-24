package data.task;

import data.exception.DukeInvalidUserInputException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDateTime dateTime;
    private String dateTimeTxt;

    public Deadline(String description, String dateTime) throws DukeInvalidUserInputException {
        super(description);
        convertDateTime(dateTime);
    }

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

    public String toTxtFormat() {
        return "D | " + super.toTxtFormat() + " | " + this.dateTimeTxt;
    }

    public static Deadline parse(String txtFormat, String[] txtArray) throws DukeInvalidUserInputException {
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
