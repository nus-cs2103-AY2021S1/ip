package duke;

import java.time.LocalDate;

public class Events extends Task {

    private String start;
    private LocalDate date;

    /**
     * Class constructor specifying the Task start date.
     * @param description
     */
    public Events(String description) {
        super(description);
        this.type = "Events";
    }

    /**
     * Sets the start time to the specified string.
     * @param start
     */
    public void setStart(String start) {
        this.start = start;
    }

    /**
     * Returns the start time of the Event.
     * @return the start time in String.
     */
    public String getStart() {
        return this.start;
    }

    public LocalDate getDate() {
        return this.date;
    }

    /**
     * Sets the LocalDate of the Event.
     */
    public void setDateTime() {
        int index = this.start.indexOf(" ");
        String dateTemp = this.start.substring(0, index);
        LocalDate date = LocalDate.parse(dateTemp);
        this.date = date;
    }

    /**
     * Returns string description of event.
     * @return String description of event
     */
    public String toString() {
        return "  [" + this.getType() + "][" + this.getStatusIcon() + "] "
                + this.getDescription().substring(0, description.indexOf("/")) + "(at: " + this.getStart() + ")";
    }
}
