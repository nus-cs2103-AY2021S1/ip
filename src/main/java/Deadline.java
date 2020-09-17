import java.time.LocalDate;

/**
 * A class that contains Deadline tasks with the type "D".
 */
public class Deadline extends Task {

    private final String type;

    /**
     * Constructor.
     * @param desc the description of the task.
     * @param isDone the status icon of the task.
     * @param time the time of the task.
     */
    public Deadline(String desc, boolean isDone, LocalDate time) {
        super(desc, isDone, time);
        type = "D";
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
