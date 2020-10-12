package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Event extends Task {
    protected String at;
    protected LocalDate date;

    /**
     * The constructor of the Event object which is extended from Task{@link Task}. It takes in a task description as
     * well as an "at" String which represents when the event will be held, formatted in FORMATSTYLE.FULL.
     *
     * @param description The description of the Event object
     * @param at the date the deadline should be done by. It should follow the format "yyyy-MM-dd".
     */
    public Event(String description, String at) {
        super(description);
        LocalDate localDate = LocalDate.parse(at, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.date = localDate;
        this.at = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }

    /**
     * Simply checks if the "at" String observes the format "yyyy-MM-dd".
     *
     * @param date The String which represents by when the deadline is due.
     * @return True if the date String follows the format "yyyy-MM-dd".
     * @throws DukeException thrown if the "by" String does not follow the specified format, or if the date is invalid
     * e.g. the 50th of June.
     */
    public static boolean checkDateFormat(String date) throws DukeException {
        int stringLength = date.length();
        boolean invalidDateString = stringLength != 10;
        if (invalidDateString) {
            throw new DukeException("That is not a valid date!");
        } else {
            try { //Further checks if the date String is a valid date format
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return true;
            } catch (DateTimeParseException e) {
                throw new DukeException("Please check your date! It is clearly not realistic >:(");
            }
        }
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
