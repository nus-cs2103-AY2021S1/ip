package duke;

public class Event extends Task {
    private String date;

    /**
     * Constructor instantiates event task, uses parent class Task's constructor
     * @param description the String description of the event task
     * @param date the String date where event is occurring
     */
    public Event(String description, String date) {
        super(description);
        this.date = date;
    }

    @Override
    public String getTaskType() {
        return "E";
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + "[" + getStatusIcon() + "]" + description + " " + "(at: " + date + ")";
    }
}
