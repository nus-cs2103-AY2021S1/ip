/**
 * Represents a Deadline Task. A <code>Deadline</code> object contains a description,
 * keeps track of whether it has been completed as well as the time.
 */
public class Deadline extends TimedTask {

    public Deadline(String description, String deadline) {
        super(description, deadline);
        super.type = Task.Type.DEADLINE;
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description, deadline);
        super.type = Task.Type.DEADLINE;
        super.isDone = isDone;
    }
}