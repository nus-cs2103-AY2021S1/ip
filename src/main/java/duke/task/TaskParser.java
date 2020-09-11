package duke.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import duke.DukeException;
import duke.util.Pair;

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

    protected static final String SC_RECURRING = "-R";
    protected static final List<String> VALID_SUBCOMMANDS = new ArrayList<>();
    static {
        VALID_SUBCOMMANDS.add(SC_RECURRING);
    }

    /**
     * Parses a task description and returns the corresponding task if the description is valid.
     *
     * @param taskDescription description of the task, starting with the type as the first word.
     * @return task corresponding to the description given.
     * @throws DukeException if the command given is not a valid type.
     */
    public static Task parse(String taskDescription) throws DukeException {
        Pair<String, HashMap<String, String>> pair = parseForSubcommands(taskDescription);
        String newTaskDescription = pair.getFirst();
        HashMap<String, String> subcommands = pair.getSecond();

        String[] details = newTaskDescription.split(" ", 2);
        if (!VALID_TASK_TYPES.contains(details[0])) {
            throw new DukeException(details[0] + " is not a valid type of command!");
        }
        if (details.length == 1 || details[1].isBlank()) {
            throw new DukeException("The description of a " + details[0] + " task cannot be empty.");
        }

        Task addedTask;
        switch (details[0]) {
        case TO_DO:
            addedTask = new ToDo(details[1]);
            break;
        case DEADLINE:
            if (subcommands.containsKey(SC_RECURRING)) {
                addedTask = new RecurringDeadline(details[1], subcommands.get(SC_RECURRING));
            } else {
                addedTask = new Deadline(details[1]);
            }
            break;
        case EVENT:
            if (subcommands.containsKey(SC_RECURRING)) {
                addedTask = new RecurringEvent(details[1], subcommands.get(SC_RECURRING));
            } else {
                addedTask = new Event(details[1]);
            }
            break;
        default:
            throw new DukeException(details[0] + " is not a valid type of command!");
        }
        return addedTask;

    }

    private static Pair<String, HashMap<String, String>> parseForSubcommands(String taskDescription) {
        String[] details = taskDescription.split(" ");
        HashMap<String, String> subcommands = new HashMap<>();
        String currentCommand = "";
        String firstFoundCommand = null;
        String upperCaseDetails;
        boolean isPreviousDetailSubcommand = false;
        for (String detail : details) {
            upperCaseDetails = detail.toUpperCase();
            if (isPreviousDetailSubcommand) {
                subcommands.put(currentCommand, detail);
                isPreviousDetailSubcommand = false;
                continue;
            }
            if (!VALID_SUBCOMMANDS.contains(upperCaseDetails)) {
                continue;
            }
            if (firstFoundCommand == null) {
                firstFoundCommand = detail;
            }

            isPreviousDetailSubcommand = true;
            currentCommand = upperCaseDetails;
        }

        String newTaskDescription = firstFoundCommand == null
                ? taskDescription
                : taskDescription.split(" " + firstFoundCommand, 2)[0];
        return Pair.of(newTaskDescription, subcommands);
    }


}
