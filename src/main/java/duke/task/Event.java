package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles instances of Event, a subclass of Task that involves description and LocalDate
 */
public class Event extends Task {

    private String identifier;
    private LocalDate date;

    /**
     * Constructor for Event instance
     *
     * @param description includes specific details of event, and date in YYYY-MM-DD format
     */
    public Event(String description) {
        super(description);
        this.identifier = "E";

        String[] desArray = this.description.split("/", 2); //e.g. do homework/ by 1999-10-21
        String[] dateArray = desArray[1].split(" ", 2); //1999-10-21
        this.date = LocalDate.parse(dateArray[1]);
    }

    /**
     * Getter function to retrieve identifier to help with writing to duke.txt file
     *
     * @return
     */
    @Override
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Separates the date from description and reformats the date to MMM D YYY
     *
     * @return
     */
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }


    /**
     * Getter function that separates the event details from the date
     *
     * @return
     */
    @Override
    public String getDescription() {
        String fullDescription = super.getDescription();
        String[] desArray = fullDescription.split("/", 2);
        return desArray[0];
    }

    @Override
    public LocalDate getLocalDate() {
        return this.date;
    }

    @Override
    public String toString() {
        String[] desArray = this.description.split("/", 2);
        String[] timeArray = desArray[1].split(" ", 2);

        return "[E]" + "[" + getStatusIcon() + "] " + getDescription() + "(" + timeArray[0] + ": " + getDate() + ")";
    }
}
