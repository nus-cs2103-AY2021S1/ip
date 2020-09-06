package duke.task;

import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidTaskException;

// Deals with the creation of the different types of tasks.
public class TaskFactory {
    /**
     * Calls the factory method of each type of tasks based on the task type specified.
     *
     * @param type TaskType type of task to create.
     * @param details String details of the task.
     * @return Task the specified type of task along with the details.
     * @throws InvalidTaskException If the details provided have an invalid format.
     */
    public static Task createTask(TaskType type, String details) throws DukeException {
        switch (type) {
        case Todo:
            return Todo.createTodo(details);
        case Deadline:
            return Deadline.createDeadline(details);
        case Event:
            return Event.createEvent(details);
        default:
            assert type.equals(TaskType.Invalid) : "TaskType should be Invalid";
            throw new InvalidCommandException("Something went wrong during the creation of the task. :-(");
        }
    }
}
