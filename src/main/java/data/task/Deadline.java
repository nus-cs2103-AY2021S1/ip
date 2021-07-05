package data.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import data.exception.DukeInvalidUserInputException;

/**
 * A specific type of task that contains a description of a task and a specific date and time.
 */
public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;
    private String dateTimeTxt;

    /**
     * Constructs a deadline task.
     * @param description of task.
     * @param dateTime of deadline task.
     * @throws DukeInvalidUserInputException when date time is incorrect format.
     */
    public Deadline(String description, String dateTime) throws DukeInvalidUserInputException {
        super(description);
        convertDateTime(dateTime);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }
    /**
     * Converts the given string into a LocalDateTime to be stored in the given Deadline.
     * @param dateTime to be converted into LocalDateTime.
     * @throws DukeInvalidUserInputException when an invalid a date time format is used as input.
     */
    private void convertDateTime(String dateTime) throws DukeInvalidUserInputException {
        try {
            String[] dateTimeArr = dateTime.split(" ");
            this.date = LocalDate.parse(dateTimeArr[0]);
            this.time =
                    LocalTime.parse(dateTimeArr[1].substring(0, 2) + ":" + dateTimeArr[1].substring(2, 4));
            this.dateTimeTxt = this.date.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) + " "
                    + this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
            //Assertion to check whether date and time have been correctly parsed
            assert this.date != null;
            assert this.time != null;
        } catch (DateTimeParseException e) {
            throw new DukeInvalidUserInputException("It seems you have entered an invalid date and time. "
                    + "The format should be as follows YYYY-MM-DD hhmm.");
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
        //Assertions to check whether the txtArray are of the correct format.
        assert txtArray[1] != null; //This field should not be empty unless user manually modify TaskList.txt.
        assert txtArray[2] != null; //This field should not be empty unless user manually modify TaskList.txt.
        assert txtArray[3] != null; //This field should not be empty unless user manually modify TaskList.txt.

        String done = txtArray[1].trim();
        String description = txtArray[2].trim();
        String[] unFormattedDateTime = txtArray[3].trim().split(" ");
        String[] formattedDateTime = formatDateTime(unFormattedDateTime);
        String finalDateTime = formattedDateTime[0] + " " + formattedDateTime[1];
        Deadline deadline = new Deadline(description, finalDateTime);
        if (done.equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    private static String[] formatDateTime(String[] unFormattedDateTime) {
        String[] formattedDateTime = new String[2];

        String unformattedDate =
                unFormattedDateTime[0] + " " + unFormattedDateTime[1] + " " + unFormattedDateTime[2];
        String unformattedTime =
                unFormattedDateTime[3] + " " + unFormattedDateTime[4];

        String formattedDate =
                LocalDate.parse(unformattedDate, DateTimeFormatter.ofPattern("d MMMM yyyy")).toString();
        String time =
                LocalTime.parse(unformattedTime, DateTimeFormatter.ofPattern("hh:mm a")).toString();

        String formattedTime = time.substring(0, time.indexOf(':'))
                + time.substring(time.indexOf(':') + 1);

        formattedDateTime[0] = formattedDate;
        formattedDateTime[1] = formattedTime;
        return formattedDateTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTimeTxt + ")";
    }
}
