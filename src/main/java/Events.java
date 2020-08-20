public class Events extends Task {
    protected String on;

    /**
     * Creates a type of task with timing
     *
     * @param description detail of the task
     * @param on          time the task is on
     */
    public Events(String description, String on) {
        super(description);
        this.on = on;
    }


    public Events(String description, boolean isDone, String on) {
        super(description, isDone);
        this.on = on;
    }


    /**
     * Overrides the toString method
     *
     * @return a custom event description
     */
    @Override
    public String toString() {
        return "[E][" + super.getStatusIcon() + "] " + super.description + " (at: " + this.on + ")";
    }


    // D | 0 | return book | June 6th
    public String toCustomString() {
        return "E | " + (super.isDone ? 1 : 0) + " | " + super.description + " | " + on;
    }


}