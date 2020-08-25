package data.task;

import data.exception.DukeInvalidUserInputException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;
    private String dateTimeTxt;

    public Deadline(String description, String dateTime) throws DukeInvalidUserInputException {
        super(description);
        convertDateTime(dateTime);
    }

    public LocalDate getDate() {
        return this.date;
    }

    private void convertDateTime(String dateTime) throws DukeInvalidUserInputException {
        try {
            String[] dateTimeArr = dateTime.split(" ");
            this.date = LocalDate.parse(dateTimeArr[0]);
            this.time
                    = LocalTime.parse(dateTimeArr[1].substring(0, 2) + ":" + dateTimeArr[1].substring(2, 4));
            this.dateTimeTxt = this.date.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + " "
                    + this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (DateTimeParseException e) {
            throw new DukeInvalidUserInputException("It seems you have entered an invalid date and time. "
                    + "The format should be as follows YYYY-MM-DD hhmm.");
        }
    }

    public String toTxtFormat() {
        return "D | " + super.toTxtFormat() + " | " + this.dateTimeTxt;
    }

    public static Deadline parse(String[] txtArray) throws DukeInvalidUserInputException {
        String done = txtArray[1].trim();
        String description = txtArray[2].trim();
        String[] unFormattedDateTime = txtArray[3].trim().split(" ");
        String unformattedDate
                = unFormattedDateTime[0] + " " + unFormattedDateTime[1] + " " + unFormattedDateTime[2];
        String unformattedTime
                = unFormattedDateTime[3] + " " + unFormattedDateTime[4];

        String formattedDate
                = LocalDate.parse(unformattedDate, DateTimeFormatter.ofPattern("d MMMM yyyy")).toString();
        String time
                = LocalTime.parse(unformattedTime, DateTimeFormatter.ofPattern("hh:mm a")).toString();

        String formattedTime = time.substring(0, time.indexOf(':'))
                + time.substring(time.indexOf(':') + 1);

        String finalDateTime = formattedDate + " " + formattedTime;
        Deadline deadline = new Deadline(description,finalDateTime);
        if (done.equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTimeTxt + ")";
    }
}