package commands;

import java.time.LocalDateTime;

import exceptions.DukeException;
import ui.DateConverter;



/**
 * Commands.Deadline class creates a type of Commands.
 * Task called deadline which contains a date and description of the task.
 */


public class Deadline extends Task {

    private static final int dividerNum = 4;
    private LocalDateTime date;

    /**
     * Constructor to create a Commands.Deadline Object
     * @param description description of deadline
     * @param date the date of the deadline
     */
    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * creates a deadline a string
     *
     * @param str input form user
     * @return Commands.Deadline object
     */
    public static Deadline createDeadline(String str) {
        if (str == null) {
            throw new DukeException("Luigi wonders why the string is null");
        }
        String[] detailsArray = str.split("/by", 2);
        try {
            String description = detailsArray[0].trim();
            String dateTimeString = detailsArray[1].trim();
            LocalDateTime dateTime = DateConverter.parseString(dateTimeString);
            return new Deadline(description, dateTime);
        } catch (Exception e) {
            throw new DukeException("No valid date found");
        }
    }

    /**
     * converts a String into a condensed form
     *
     * @return condesnsed form of inputted string
     */
    public String encode() {
        return String.format("D|%s|%s|%s", super.isDone ? "Y" : "N", this.date, super.description);
    }

    /**
     * unravels encoded Strings
     *
     * @param code String that has been previously encoded()
     * @return Commands.Deadline object
     * @throws DukeException in the event it is unable to decode the string
     */
    public static Deadline decode(String code) throws DukeException {
        if (code.charAt(0) == 'D') {
            String[] content = code.split("\\|", dividerNum);

            if (content.length != dividerNum) {
                throw new DukeException("data string is not equal to 4");
            }

            Deadline newDeadline = new Deadline(content[3], DateConverter.parseString(content[2]));
            if (content[1].equals("Y")) {
                newDeadline.markAsDone();
            }
            return newDeadline;
        } else {
            throw new DukeException("Unable to decode Commands.Deadline");
        }
    }


    /**
     * overrides Commands.Deadline String output to be formatted
     * @return String of formatted Commands.Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateConverter.parseLocalDateTime(this.date) + ")";
    }
}
