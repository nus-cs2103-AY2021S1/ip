package duke.tasks;

import duke.DukeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    protected LocalDate date;
    protected String time;
    protected String userInputDate;
    protected String userInputTime;

    private Event(String description, String userInputDate, String userInputTime,
                  LocalDate date, String time) {
        super(description);
        this.userInputDate = userInputDate;
        this.userInputTime = userInputTime;
        this.date = date;
        this.time = time;
    }

    /**
     * Returns Event created from the input description and date and time.
     * If input is in correct format for creation of Event,
     * method adds task to the task list creates the Event task.
     *
     * @param description String description of the task.
     * @param at String date and time of the task.
     *
     * @return Event event task created from the description and date and time.
     * @throws DukeException  If String at contains invalid format of date or time.
     */
    public static Event createEvent(String description, String at) throws DukeException {
        String[] dateAndTime = at.split(" ");

        try {
            String userInputDate = dateAndTime[0];
            String userInputTime = dateAndTime[1];
            LocalDate date = LocalDate.parse(userInputDate);
            String[] startAndEndTime = userInputTime.split("-");
            String time = timeFormat(startAndEndTime[0]) + " - " + timeFormat(startAndEndTime[1]);
            return new Event(description, userInputDate, userInputTime, date, time);
        } catch(DateTimeParseException | ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Rawr! Dino could not add your task. "
                    + "Make sure your format is correct."
                    + "\nFormats to input a task can be found by entering 'format'.");
        }
    }

    /**
     * Returns String time formatted from 24hour-24hour format to 12hour-12hour format.
     * If input is in correct 24hour-24hour format, method converts this
     * 24hour time format to 12hour-12hour time format
     * (eg. 1800-2100 converts to 6.00pm - 9.00pm).
     *
     * @param time String time in 24hour-24hour (eg. hhmm-hhmm) format that user has input.
     *
     * @return String time converted from 24hour-24hour format to 12hour-12hour format.
     * @throws DukeException  If String time contains invalid format of time.
     */
    private static String timeFormat(String time) throws DukeException {
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(2, 4));

        StringBuilder formattedTime = new StringBuilder();
        if (hour >= 12 && hour < 24 && min < 60 && min >= 0) {
            formattedTime.append(hour-12).append(".").append(String.format("%02d", min)).append("pm");
        } else if (hour > 0 && hour < 12 && min < 60 && min >= 0) {
            formattedTime.append(hour).append(".").append(String.format("%02d", min)).append("am");
        } else if (hour == 0 && min < 60 && min >= 0) {
            formattedTime.append("12").append(".").append(String.format("%02d", min)).append("am");
        } else {
            throw new DukeException("Rawr! Dino could not add your task. "
                    + "Make sure your format is correct."
                    + "\nFormats to input a task can be found by entering 'format'.");
        }
        return formattedTime.toString();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: "
                + this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ", " + this.time + ")";
    }

    /**
     * Returns string of this task to be stored in the hard disk.
     *
     * @return String task description to be stored in hard disk.
     */
    public String storedTaskString() {
        return "E" + "@" + super.storedTaskString()
                + "@" + this.userInputDate + "@" + this.userInputTime;
    }

}
