
/**
 * Represents a Event class and consists of methods related to Event Task assigned.
 */
public class Event extends Task {

    protected String duration;

    /**
     * Contructor for an event object.
     * @param newTask Name of event.
     * @param duration Time at which event happens.
     */
    public Event(String newTask, String duration) {
        super(newTask);
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
        TimeParser inputTime = new TimeParser(duration);
        String outputTime = inputTime.timeConverter();
        return "E" + " | " + super.fileFormat() + " | " + outputTime;
    }

    /**
     * Override the toString() method to suit the need of the EVENT class.
     */
    @Override
    public String toString() {
        TimeParser inputTime = new TimeParser(duration);
        String outputTime = inputTime.timeConverter();
        return "E" + " | " + super.fileFormat() + " | " + outputTime;
    }
}

