package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import duke.DukeException;

/**
 * <code>Event</code> class extends the <code>Task</code> class. Represents a <code>Task</code> with a specified
 * duration.
 */
public class Event extends Task {
    private final LocalDate schedule;
    private final LocalTime startTime;
    private final LocalTime endTime;

    Event(String description, LocalDate schedule, LocalTime startTime, LocalTime endTime) {
        super(description);
        this.schedule = schedule;
        this.startTime = startTime;
        this.endTime = endTime;
        Locale.setDefault(Locale.UK);
    }

    /**
     * Constructs a <code>Event</code> object with the following states.
     *
     * @param description the description of this event to be stored
     * @param schedule the time at which this event occurs
     * @param isDone the status of this event
     */
    public Event(String description, String schedule, Boolean isDone) {
        super(description, isDone);
        Locale.setDefault(Locale.UK);
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
    public static Event createTask(String message) throws DukeException {
        String errMessage1 = " Oops!! You missed out some vital information/keyword... *woof*\n";
        String errMessage2 = " Oops!! Are you planning to ghost the event?"
                + " You didnt state the time of this event... *woof*\n";
        String errMessage3 = " Oops!! You gonna forget what this is about if you"
                + " dont give me a description... *woof*\n";
        try {
            String messageLowerCase = message.toLowerCase();
            int indOfDescription = messageLowerCase.indexOf("event");
            int indOfTime = messageLowerCase.indexOf("/at");
            String description = message.substring(indOfDescription + 5, indOfTime).trim();
            String at = message.substring(indOfTime + 3).trim();

            if (description.isBlank() && at.isBlank()) {
                throw new DukeException(errMessage1);
            } else if (at.isBlank()) {
                throw new DukeException(errMessage2);
            } else if (description.isBlank()) {
                throw new DukeException(errMessage3);
            } else {
                String[] splitEventTime = at.split("\\s+");

                try {
                    String[] inputDate = splitEventTime[0].split("/");
                    String formatDate = inputDate[0] + "-" + inputDate[1] + "-" + inputDate[2];
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

                    LocalDate date = LocalDate.parse(formatDate, formatter);
                    String[] times = splitEventTime[1].trim().split("-");
                    LocalTime startTime = LocalTime.parse(times[0]);
                    LocalTime endTime = LocalTime.parse(times[1]);
                    checkTime(startTime, endTime);
                    return new Event(description, date, startTime, endTime);
                } catch (DukeException e) {
                    throw e;
                } catch (Exception e) {
                    String errMessage = " Please input event time in the following format:\n "
                            + "   YYYY/MM/DD HH:MM-HH:MM!\n" + " *Woof woof*\n";
                    throw new DukeException(errMessage);
                }
            }

        } catch (DukeException e) {
            throw e;
        } catch (Exception e) {
            throw new DukeException(errMessage1);
        }
    }

    /**
     * Compare the start time of an event with the end time.
     *
     * @param startTime start time of the event
     * @param endTime end time of the event
     * @throws DukeException if start time is before or same as end time.
     */
    public static void checkTime(LocalTime startTime, LocalTime endTime) throws DukeException {
        if (startTime.compareTo(endTime) > 0) {
            throw new DukeException(" You can't end the event before the start time! Woof!\n");
        } else if (startTime.compareTo(endTime) == 0) {
            throw new DukeException(" Start time cannot be the same as end time! Woof!\n");
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
     * Returns a string representation of this <code>Task</code> object for saving.
     *
     * @return a string representation of this <code>Task</code> object for saving
     */
    @Override
    public String toSaveFormat() {
        return "[E]" + super.toSaveFormat() + " (APPEAR at: "
                + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(schedule)
                + " " + startTime.toString() + "-" + endTime.toString() + ")";
    }

    /**
     * Returns a string representation of this <code>Event</code> object.
     *
     * @return a string representation of this <code>Event</code> object
     */
    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("h:mma");
        return "[E]" + super.toString() + " (APPEAR at: "
                + DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG).format(schedule)
                + " " + startTime.format(dateTimeFormatter) + " - " + endTime.format(dateTimeFormatter) + ")";
    }
}
