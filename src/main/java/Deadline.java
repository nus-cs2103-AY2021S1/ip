/**
 * The Deadline class is a Task that has a due time, represented as a TaskDate.
 *
 * @author Jaya Rengam
 */
public class Deadline extends Task {
    private TaskDate dueTime;

    Deadline(String name, boolean isDone, TaskDate dueTime) {
        super(name, isDone, "D");
        this.dueTime = dueTime;
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.dueTime);
    }

    @Override
    public String getAbbreviatedString() {
        int isDoneRep = this.isDone ? 1 : 0;
        return String.format("%s | %d | %s | %s", this.type, isDoneRep, this.name, this.dueTime);
    }
}
