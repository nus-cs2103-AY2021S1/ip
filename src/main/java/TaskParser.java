import java.util.ArrayList;
import java.util.List;

public class TaskParser {

    private static final String TO_DO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    private static final List<String> validTaskTypes;

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
            throw new DukeException("Not a valid type of command!");
        }
        return addedTask;

    }


}
