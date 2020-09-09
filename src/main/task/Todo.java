package main.task;

/**
 * Represents todo tasks.
 * @author Joshua Liang XingYa
 * @author joshualiang.xy@gmail.com
 * @version v0.3
 * @since v0.1
 */
public class Todo extends Task {

    /**
     * Constructs a Todo instance with the name of the task.
     * @param name the name of the task.
     * @param tags the tags associated with the task.
     */
    public Todo(String name, String[] tags) {
        super(name, tags);
    }

    /**
     * Constructs a Todo instance with the name of the task
     * and the done state of the task.
     * @param name the name of the task.
     * @param isDone the done state of the task.
     * @param tags the tags associated with the task.
     */
    public Todo(String name, boolean isDone, String[] tags) {
        super(name, isDone, tags);
    }

    /**
     * Returns the string meant for writing to disk.
     * @return the string meant for writing to disk.
     */
    @Override
    public String write() {
        return String.format("T,,%s", super.write());
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Todo) {
            Task o = (Task) obj;

            return super.equals(o);
        }
        return false;
    }
}
