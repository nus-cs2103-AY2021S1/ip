import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task{

    protected String at;
    protected LocalDate date;
    protected String time;

    public Event(String description, String at) throws DukeException {
        super(description);
        this.at = at;
        this.dateAndTimeFormat(at);
    }

    public void dateAndTimeFormat(String by) throws DukeException {
        String[] dateAndTime = by.split(" ");
        try {
            this.date = LocalDate.parse(dateAndTime[0]);
            String[] startAndEndTime = dateAndTime[1].split("-");
            this.time = timeFormat(startAndEndTime[0]) + " - " + timeFormat(startAndEndTime[1]);

        } catch(DateTimeParseException e) {
            throw new DukeException("Rawr! Dino could not add your task. "
                    + "Make sure your format is correct."
                    + "\nFormats to input a task can be found by entering 'format'.");
        }
    }

    public String timeFormat(String time) throws DukeException {
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
}
