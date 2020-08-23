package duke.task;

import duke.DukeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * <code>Event</code> class extends the <code>Task</code> class. Represents a <code>Task</code> with a specified
 * duration.
 */
public class Event extends Task{
    LocalDate schedule;
    LocalTime startTime;
    LocalTime endTime;

    Event(String description, LocalDate schedule, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.schedule = schedule;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public Event(String description, String schedule, Boolean isDone) {
        super(description, isDone);
        String[] scheduledTime = schedule.split(" ");
        this.schedule = LocalDate.of(Integer.parseInt(scheduledTime[2]),
                Month.valueOf(scheduledTime[1].trim().toUpperCase()).getValue(),
                Integer.parseInt(scheduledTime[0]));
        String[] times = scheduledTime[3].split("-");
        this.startTime = LocalTime.parse(times[0]);
        this.endTime = LocalTime.parse(times[1]);
    }

    /**
     * Returns a <code>Event</code> object if input format is correct. Specifically, the input format of
     * <code>Event</code> object must be in the form of "Event description /by YYYY/MM/DD HH:MM-HH:MM"
     *
     * @param message the command to create an <code>Event</code> object
     * @return an <code>Event</code> object
     * @throws DukeException if the input format is wrong or contains missing details
     */
    public static Event createTask(String message) throws DukeException{
        String errMessage1 = " Oops!! You missed out some vital information/keyword... *woof*\n";
        String errMessage2 = " Oops!! Are you planning to ghost the event?\n" +
                " You didnt state the time of this event... *woof*\n";
        String errMessage3 = " Oops!! You gonna forget what this is about if you\n" +
                " dont give me a description... *woof*\n";
        try {
            String messageLowerCase = message.toLowerCase();
            int indOfTime = messageLowerCase.indexOf("/at");
            String description = message.substring(6, indOfTime);
            String at = message.substring(indOfTime + 3).trim();
            if (description.isBlank() && at.isBlank()) {
                String exMessage = Task.ui.printFormat(errMessage1);
                throw new DukeException(exMessage);
            } else if (at.isBlank()) {
                String exMessage = Task.ui.printFormat(errMessage2);
                throw new DukeException(exMessage);
            } else if (description.isBlank()) {
                String exMessage = Task.ui.printFormat(errMessage3);
                throw new DukeException(exMessage);
            } else {
                String[] splitEventTime = at.split("\\s+");

                try {
                    String[] inputDate = splitEventTime[0].split("/");
                    String formatDate = inputDate[0] + "-" + inputDate[1] + "-" + inputDate[2];

                    LocalDate date = LocalDate.parse(formatDate);
                    String[] times = splitEventTime[1].trim().split("-");
                    LocalTime startTime = LocalTime.parse(times[0]);
                    LocalTime endTime = LocalTime.parse(times[1]);
                    return new Event(description, date, startTime, endTime);
                } catch (Exception e) {
                    String errMessage = Task.ui.printFormat(" Please input event time in the following format:\n "
                            + "   YYYY/MM/DD HH:MM-HH:MM!\n" + " *Woof woof*\n");
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
     * Compare the date of this <code>Event</code> task with the specified date.
     *
     * @param date the specified Date
     * @return returns true if the date of this <code>Event</code> task is same as the specified date.
     * Else, otherwise.
     */
    @Override
    public boolean compareDate(LocalDate date) {
        return schedule.compareTo(date) == 0;
    }

    /**
     * Returns a string representation of this <code>Event</code> object.
     *
     * @return a string representation of this <code>Event</code> object
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (APPEAR at: "
                + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(schedule)
                + " " + startTime + "-" + endTime + ")";
    }
}
