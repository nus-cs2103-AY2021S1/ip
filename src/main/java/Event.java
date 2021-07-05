import java.time.LocalDateTime;

public class Event extends Task {

    protected String time;
    protected LocalDateTime startTime;
    protected LocalDateTime endTime;

    /**
     * Creates an Event object that represents an event.
     *
     * @param description Description of the event.
     * @param time        The time of the event.
     */
    public Event(String description, String time) {
        super(description);

        String[] dateAndTime = time.split(" ");
        assert dateAndTime.length == 2 : "Event inputted does not follow required format";

        String[] date = dateAndTime[0].split("/");
        String[] eventTimeArr = dateAndTime[1].split("-");

        LocalDateTime startTime = LocalDateTime.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]),
                Integer.parseInt(date[0]), Integer.parseInt(eventTimeArr[0].substring(0, 2)),
                Integer.parseInt(eventTimeArr[0].substring(2)));

        LocalDateTime endTime = LocalDateTime.of(Integer.parseInt(date[2]), Integer.parseInt(date[1]),
                Integer.parseInt(date[0]), Integer.parseInt(eventTimeArr[1].substring(0, 2)),
                Integer.parseInt(eventTimeArr[1].substring(2)));

        this.time = time;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Returns the Event time.
     *
     * @return The Event time.
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Formats the time string to be printed out.
     *
     * @return the formatted string for the time.
     */
    private String formatTime(LocalDateTime dateTime) {
        String period = dateTime.getHour() >= 12 ? "PM" : "AM";
        String hour = dateTime.getHour() > 12
                ? Integer.toString(dateTime.getHour() - 12)
                : Integer.toString(dateTime.getHour());

        if (dateTime.getMinute() > 0) {
            return hour + "." + dateTime.getMinute() + period;
        } else {
            return hour + period;
        }
    }

    @Override
    public String toString() {
        String formattedStartTime = formatTime(startTime);
        String formattedEndTime = formatTime(endTime);
        return "[E]" + super.toString() + " (at: " + startTime.getDayOfMonth() + " " + startTime.getMonth() + " "
                + startTime.getYear() + " " + formattedStartTime + " to " + formattedEndTime + ")";

    }
}
