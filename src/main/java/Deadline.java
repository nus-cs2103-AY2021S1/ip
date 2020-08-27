/**
 * A class that contians Deadline tasks with the type "D".
 */

import java.time.LocalDate;

public class Deadline extends Task {

    public String type;

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
