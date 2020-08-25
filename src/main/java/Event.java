public class Event extends Task {

    protected String duration;

    /**
     * Contructor for an event object.
     * @param new_task Name of event.
     * @param duration Time at which event happens.
     */
    public Event(String new_task, String duration) {
        super(new_task);
        this.duration = duration;
    }

    /**
     * Return the file format form for the task.
     */
    public String fileFormat() {
        return "E" + " | " + super.fileFormat() + " | " + duration;
    }

    /**
     * Return the converted time form of the task.
     */
    public String timeConverted() {
        timeParser inputTime = new timeParser(duration);
        String outputTime = inputTime.timeConverter();
        return "E" + " | " + super.fileFormat() + " | " + outputTime;
        
    }

    @Override
    public String toString() {
        return "[E]" +  super.toString() + " (at: " + duration + ")";
    }
}

