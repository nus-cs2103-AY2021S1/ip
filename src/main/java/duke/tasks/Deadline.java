package duke.tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.DukeException;

/**
 * The Deadline class implements a Deadline task
 * with task description and deadline.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
public class Deadline extends Task {

    protected String userInputDate;
    protected String userInputTime;
    protected LocalDate date;
    protected String time;

    /**
     * Instantiates a Deadline task.
     *
     * @param description string description of the task
     * @param userInputDate string date of task in yyyy-mm-dd format
     * @param userInputTime string time of task in hhmm (24-hour) format
     * @param date LocalDate parsed date of task
     * @param time String time of task in 12-hour format
     */
    private Deadline(String description, String userInputDate, String userInputTime,
                     LocalDate date, String time) {
        super(description);
        this.userInputDate = userInputDate;
        this.userInputTime = userInputTime;
        this.date = date;
        this.time = time;
    }

    /**
     * Returns Deadline task created from the input description and date and time.
     * If input is in correct format for creation of Deadline,
     * method adds task to the task list creates the Deadline task.
     *
     * @param description String description of the task.
     * @param by String deadline date and time of the task.
     *
     * @return Deadline event task created from the description and date and time.
     * @throws DukeException  If String at contains invalid format of date or time.
     */
    public static Deadline createDeadline(String description, String by) throws DukeException {
        String[] dateAndTime = by.split(" ");

        try {
            String userInputDate = dateAndTime[0];
            String userInputTime = dateAndTime[1];

            LocalDate date = LocalDate.parse(userInputDate);
            String time = timeFormat(userInputTime);

            return new Deadline(description, userInputDate, userInputTime, date, time);

        } catch (DateTimeParseException | DukeException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Rawr! Dino could not add your task. "
                    + "Make sure your format is correct."
                    + "\nFormats to input a task can be found by entering 'format'.");
        }
    }

    /**
     * Returns String time formatted from 24hour format to 12hour format.
     * If input is in correct 24hour format, method converts this
     * 24hour time format to 12hour time format (eg. 1800 converts to 6.00pm).
     *
     * @param time String time in 24hour (eg. hhmm) format that user has input.
     *
     * @return String time converted from 24hour format to 12hour format.
     * @throws DukeException  If String time contains invalid format of time.
     */
    private static String timeFormat(String time) throws DukeException {
        try {
            int hour = Integer.parseInt(time.substring(0, 2));
            int min = Integer.parseInt(time.substring(2, 4));

            StringBuilder formattedTime = new StringBuilder();
            if (hour >= 12 && hour < 24 && min < 60 && min >= 0) {
                formattedTime.append(hour - 12).append(".")
                        .append(String.format("%02d", min)).append("pm");
            } else if (hour > 0 && hour < 12 && min < 60 && min >= 0) {
                formattedTime.append(hour).append(".")
                        .append(String.format("%02d", min)).append("am");
            } else if (hour == 0 && min < 60 && min >= 0) {
                formattedTime.append("12").append(".")
                        .append(String.format("%02d", min)).append("am");
            } else {
                assert (hour > 24 || hour < 0);
                assert (min > 60 || min < 0);
                throw new DukeException("Rawr! Dino could not add your task. "
                        + "Make sure your format is correct."
                        + "\nFormats to input a task can be found by entering 'format'.");
            }
            return formattedTime.toString();

        } catch (NumberFormatException e) {
            throw new DukeException("Rawr! Dino could not add your task. "
                    + "Make sure your format is correct."
                    + "\nFormats to input a task can be found by entering 'format'.");
        }

    }

    @Override
    public String toString() {
        if (!priority.equals(Priority.NONE)) {
            return "[D]" + super.toString() + " (by: "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + ", " + this.time + ") " + "<" + this.priority + ">";
        } else {
            return "[D]" + super.toString() + " (by: "
                    + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    + ", " + this.time + ")";
        }
    }

    /**
     * Returns string of this task to be stored in the hard disk.
     *
     * @return String task description to be stored in hard disk.
     */
    public String storedTaskString() {
        return "D" + "!@#" + super.storedTaskString()
                    + "!@#" + this.userInputDate + "!@#" + this.userInputTime + "!@#" + this.priority;
    }
}
