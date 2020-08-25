package duke.task;

import duke.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.util.ArrayList;
import java.util.List;

public class TaskParser {

    protected static final String TO_DO = "todo";
    protected static final String DEADLINE = "deadline";
    protected static final String EVENT = "event";

    protected static final List<String> validTaskTypes;

    static {
        validTaskTypes = new ArrayList<>();
        validTaskTypes.add(TO_DO);
        validTaskTypes.add(DEADLINE);
        validTaskTypes.add(EVENT);
    }

    public static Task parse(String taskDescription) {
        String[] details = taskDescription.split(" ", 2);
        if (details.length == 1 && validTaskTypes.contains(details[0])) {
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
