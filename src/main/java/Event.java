import java.time.LocalDate;

/**
 * A class that contains event tasks with the type "E".
 */
public class Event extends Task {

    private final String type;

    /**
     * Constructor
     * @param desc description of the task.
     * @param isDone status icon.
     * @param time time of the task.
     */
    public Event(String desc, boolean isDone, LocalDate time) {
        super(desc, isDone, time);
        type = "E";
    }

    @Override
    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return super.toString();
    }

}
