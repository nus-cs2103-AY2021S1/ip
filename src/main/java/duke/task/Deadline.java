package duke.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Handles instances of Deadline, a subclass of Task that involves description and LocalDate
 */
public class Deadline extends Task {

    private String identifier;
    private LocalDate date;

    /**
     * Constructor for Deadline instance
     *
     * @param description includes specific details of event, and date in YYYY-MM-DD format
     */
    public Deadline(String description) {
        super(description);
        this.identifier = "D";

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
    @Override
    public String getDescription() {
        String fullDescription = super.getDescription();
        String[] desArray = fullDescription.split("/", 2);
        return desArray[0];
    }

    /**
     * Getter function that separates the event details from the date
     *
     * @return
     */
    public String getDate() {
        return this.date.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
    }

    @Override
    public LocalDate getLocalDate() {
        return this.date;
    }

    @Override
    public String toString() {
        String[] desArray = this.description.split("/", 2);
        String[] timeArray = desArray[1].split(" ", 2);
        return "[D]" + "[" + getStatusIcon() + "] " + getDescription() + "(" + timeArray[0] + ": " + getDate() + ")";
    }
}