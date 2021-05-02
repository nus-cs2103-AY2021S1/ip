package duke.task;

public class FixedDurationTask extends Task {
    private String duration;

    /**
     * Instantiate Fixed Duration Task Object.
     * @param description  Description of Event.
     * @param time  Time of event.
     */
    public FixedDurationTask(String description, String time) {
        super(description);
        this.duration = time;
    }
    @Override
    public String toString() {
        return "[FT]" + super.toString() + " (" + duration + ")";
    }
    @Override
    public String convertToData() {
        return "FT | " + (isDone ? 1 : 0) + " | " + description + " | " + duration;
    }
}
