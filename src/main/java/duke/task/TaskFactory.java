package duke.task;

import duke.exception.InvalidTaskException;

public class TaskFactory {
    public static Task createTask(TaskType type, String details) throws InvalidTaskException {
        switch (type) {
            case Todo:
                return Todo.createTodo(details);
            case Deadline:
                return Deadline.createDeadline(details);
            default: // duke.task.Event
                return Event.createEvent(details);
        }
    }
}
