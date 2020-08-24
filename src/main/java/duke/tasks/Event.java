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

    public static Event createEvent(String description, String at) throws DukeException {
        String[] dateAndTime = at.split(" ");
        String userInputDate = dateAndTime[0];
        String userInputTime = dateAndTime[1];
        try {
            LocalDate date = LocalDate.parse(userInputDate);
            String[] startAndEndTime = userInputTime.split("-");
            String time = timeFormat(startAndEndTime[0]) + " - " + timeFormat(startAndEndTime[1]);
            return new Event(description, userInputDate, userInputTime, date, time);
        } catch(DateTimeParseException e) {
            throw new DukeException("Rawr! Dino could not add your task. "
                    + "Make sure your format is correct."
                    + "\nFormats to input a task can be found by entering 'format'.");
        }
    }

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

    public String storedTaskString() {
        return "E" + "@" + super.storedTaskString()
                + "@" + this.userInputDate + "@" + this.userInputTime;
    }

}
