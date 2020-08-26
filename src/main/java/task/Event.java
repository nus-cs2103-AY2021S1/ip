package task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Event is a task that has a time this event occurs at
 *
 * @author (Sruthi)
 */
public class Event extends Task {
    private String dateTime;
    private LocalDate dueDate;
    private String input;

    /**
     * Event takes in the description of the task, the date and time,
     * due date of the task, the input String by the user and whether the
     * task is completed or not.
     *
     * @param item the description of the Event
     * @param dateTime the date and time inputted by the user
     * @param dueDate the due date of the Event
     * @param input the input given by the user
     * @param completed whether the Event is completed
     */
    public Event(String item, String dateTime, LocalDate dueDate, String input, boolean completed) {
        super(item, completed);
        this.dateTime = dateTime;
        this.dueDate = dueDate;
        this.input = input;
    }

    /**
     * It returns the input given by the user to the Java Duke Program
     *
     * @return String to be printed to the user
     */
    @Override
    public String getItem() {
        return "[E]" + super.getItem() + "(at: " + dateTime + ")";
    }

    /**
     * It returns the details of the Event
     *
     * @return String to be printed to the user
     */
    @Override
    public String getInput() {
        return "[E]" + super.getItem() + "/at " + this.input;
    }
}
