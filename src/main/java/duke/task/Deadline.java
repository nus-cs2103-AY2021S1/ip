package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * <code>Deadline</code> class extends the <code>Task</code> class. Represents the a <code>Task</code> with a deadline
 * for completion.
 */
public class Deadline extends Task {
    LocalDate toDoBy;
    LocalTime time;

    Deadline(String description, LocalDate toDoBy, LocalTime time) {
        super(description);
        this.toDoBy = toDoBy;
        this.time = time;
    }

    Deadline(String description, LocalDate toDoBy) {
        super(description);
        this.toDoBy = toDoBy;
        this.time = null;
    }

    public Deadline(String description, String toDoBy, Boolean isDone) {
        super(description, isDone);
        String[] scheduledTime = toDoBy.split(" ");
        this.toDoBy = LocalDate.of(Integer.parseInt(scheduledTime[2]),
                Month.valueOf(scheduledTime[1].trim().toUpperCase()).getValue(),
                Integer.parseInt(scheduledTime[0]));
        if (scheduledTime.length == 4) {
            this.time = LocalTime.parse(scheduledTime[3].trim());
        } else {
            this.time = null;
        }
    }

    /**
     * Returns a <code>Deadline/code> object if input format is correct. Specifically, the input format of
     * <code>Event</code> object must be in the form of "Deadline description /by YYYY/MM/DD" or
     * "Event description /by YYYY/MM/DD HH:MM".
     *
     * @param message the command to create an <code>Deadline</code> object
     * @return an <code>Deadline</code> object
     * @throws DukeException if the input format is wrong or contains missing details
     */
    public static Deadline createTask(String message) throws DukeException {
        String errMessage1 = " Oops!! You missed out some vital information/keyword... *woof*\n";
        String errMessage2 = " Oops!! You gonna forget what this is about if you\n" +
                " dont give me a description... *woof*\n";
        String errMessage3 = " Oops!! You did not state when you wanna finish this by...\n" +
                " Are you planning to procrastinate? *woof*\n";
        try {
            String messageLowerCase = message.toLowerCase();
            int indOfTime = messageLowerCase.indexOf("/by");
            String description = message.substring(9, indOfTime);
            String deadline = message.substring(indOfTime + 3).trim();
            if (description.isBlank() && deadline.isBlank()) {
                String exMessage = Task.ui.printFormat(errMessage1);
                throw new DukeException(exMessage);
            }else if (deadline.isBlank()) {
                String exMessage = Task.ui.printFormat(errMessage3);
                throw new DukeException(exMessage);
            } else if (description.isBlank()) {
                String exMessage = Task.ui.printFormat(errMessage2);
                throw new DukeException(exMessage);
            } else {
                String[] splitDeadline = deadline.split("\\s+");
                try {
                    String[] inputDate = splitDeadline[0].trim().split("/");
                    String formatDate = inputDate[0] + "-" + inputDate[1] + "-" + inputDate[2];
                    LocalDate date = LocalDate.parse(formatDate);

                    if (splitDeadline.length != 1) {
                        LocalTime time = LocalTime.parse(splitDeadline[1].trim());
                        return new Deadline(description, date, time);
                    } else {
                        return new Deadline(description, date);
                    }
                } catch (Exception e) {
                    String errMessage =
                            Task.ui.printFormat(" Please input deadline in following format:\n"
                                    + "   YYYY/MM/DD HH:MM!\n" + " *Woof woof*\n");
                    throw new DukeException(errMessage);
                }
            }
        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            String exMessage = Task.ui.printFormat(errMessage1);
            throw new DukeException(exMessage);
        }
    }

    /**
     * Compare the date of this <code>Deadline</code> task with the specified date.
     *
     * @param date the specified Date
     * @return returns true if the date of this <code>Deadline</code> task is same as the specified date. Else,
     * otherwise.
     */
    @Override
    public boolean compareDate(LocalDate date) {
        return toDoBy.compareTo(date) == 0;
    }

    /**
     * Returns a string representation of this <code>Deadline</code> object.
     *
     * @return a string representation of this <code>Deadline</code> object
     */
    @Override
    public String toString() {
        String s = "[D]" + super.toString() + " (FINISH by: "
                + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(toDoBy);
        if (time == null) {
            return s + ")";
        } else {
            return s + " " + time.toString() + ")";
        }
    }
}
