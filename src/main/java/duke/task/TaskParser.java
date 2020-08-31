package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.DukeException;

/**
 * Class handles parsing of strings inputs (from users) into the apporpriate task object.
 */
public class TaskParser {

    /** todo-type task indicator */
    protected static final String TO_DO = "todo";
    /** deadline-type task indicator */
    protected static final String DEADLINE = "deadline";
    /** event-type task indicator */
    protected static final String EVENT = "event";

    /** collections of the valid indicator strings */
    protected static final List<String> VALID_TASK_TYPES;
    static {
        VALID_TASK_TYPES = new ArrayList<>();
        VALID_TASK_TYPES.add(TO_DO);
        VALID_TASK_TYPES.add(DEADLINE);
        VALID_TASK_TYPES.add(EVENT);
    }

    /**
     * Parses a task description and returns the corresponding task if the description is valid.
     * @param taskDescription description of the task, starting with the type as the first word
     * @return task corresponding to the description given
     */
    public static Task parse(String taskDescription) {
        String[] details = taskDescription.split(" ", 2);
        if (details.length == 1 && VALID_TASK_TYPES.contains(details[0])) {
            throw new DukeException("The description of a " + details[0] + " cannot be empty.");
        }
        Task addedTask;
        switch (details[0]) {
        case TO_DO:
            addedTask = new ToDo(details[1]);
            break;
        case DEADLINE:
            addedTask = new Deadline(details[1]);
            break;
        case EVENT:
            addedTask = new Event(details[1]);
            break;
        default:
            throw new DukeException(details[0] + " is not a valid type of command!");
        }
        return addedTask;

    }


}
