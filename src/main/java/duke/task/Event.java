package duke.task;

/**
 * Defines an duke.task.Event
 */
public class Event extends Task {
    public Event(String taskName) {
        super(taskName, TaskType.E);
    }
    public Event(String taskName, Priority priority) {
        super(taskName, TaskType.E, priority);
    }
}
