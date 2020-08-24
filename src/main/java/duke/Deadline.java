package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;


public class Deadline extends Task {
    protected String by;
    protected LocalDate date;


    /**
     * Constructor of the Deadline object which extends Task{@link Task}. It stores the description of the task as well
     * as the deadline of the task, formatted in FORMATSTYLE.FULL.
     *
     * @param description the description of the Deadline task.
     * @param by the date the deadline should be done by. It should follow the format "yyyy-MM-dd".
     */
    public Deadline(String description, String by) {
        super(description);
        LocalDate localDate = LocalDate.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        this.date = localDate;
        this.by = localDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL));
    }

    /**
     * Simply checks if the "by" String observes the format "yyyy-MM-dd".
     *
     * @param date The String which represents by when the deadline is due.
     * @return True if the date String follows the format "yyyy-MM-dd".
     * @throws DukeException thrown if the "by" String does not follow the specified format, or if the date is invalid
     * e.g. the 50th of June.
     */
    public static boolean checkDateFormat(String date) throws DukeException{
        int stringLength = date.length();
        if (stringLength != 10) {
            throw new DukeException("That is not a valid date!");
        } else {
            try {
                LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                return true;
            } catch (DateTimeParseException e) {
                throw new DukeException("Please check your date! It is clearly not realistic >:(");
            }
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
