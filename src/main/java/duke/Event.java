package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected String at;

    private final LocalDate startDate;
    private LocalDate endDate;
    private final LocalTime startTime;
    private final LocalTime endTime;

    //event project meeting /at Mon 2-4pm
    public static final String delimiterAt = " /at ";

    public Event(String description, String at) {
        super(description);
        this.at = at;

        String[] dateTime = this.at.split(" ");

        // case if event last for days
        if (dateTime[0].contains("-")) {
            String[] dateSplit = dateTime[0].split("-");
            this.startDate = parseDate(dateSplit[0]);
            this.endDate = parseDate(dateSplit[1]);
        } else {
            this.startDate = parseDate(dateTime[0]);
        }

        String[] timeSplit = dateTime[1].split("-");
        this.startTime = parseTime(timeSplit[0]);
        this.endTime = parseTime(timeSplit[1]);

    }

    private LocalDate parseDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

    private LocalTime parseTime(String timeString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("Hmm");
        return LocalTime.parse(timeString, formatter);
    }

    private String atFormat() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEE, d MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("h:mma");
        if (endDate == null) {
            return String.format("%s, %s - %s", dateFormatter.format(startDate),
                    timeFormatter.format(startTime),
                    timeFormatter.format(endTime));
        } else {
            return String.format("%s - %s, %s - %s", dateFormatter.format(startDate),
                    dateFormatter.format(endDate),
                    timeFormatter.format(startTime),
                    timeFormatter.format(endTime));
        }
    }

    @Override
<<<<<<< HEAD
    public String serialize() {
        return String.format("E | %d | %s | %s", getStatusCode(), description , at);
    }

    @Override
=======
>>>>>>> 7cf06867bc1780a8ba55bc5d6537869fa9988b2d
    public String toString() {
        return "[E]" + super.toString() + " (at: " + atFormat() + ")";
    }
}
