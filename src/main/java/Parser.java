import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class Parser {
    public static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("h.mma");

    /**
     * Executes the relevant functions based on the given user command.
     *
     * @return the output from executing the command.
     */
    public String parseCommands(TaskList tasks, Ui ui, String command) throws DukeException {
        String output;
        String[] split = command.split(" ");

        switch (split[0]) {
        case "bye":
            output = ui.getExitMessage();
            break;

        case "deadline":
            output = tasks.addNewDeadline(Arrays.copyOfRange(split, 1, split.length));
            break;

        case "delete":
            output = tasks.deleteTask(split[1]);
            break;

        case "done":
            output = tasks.markTaskAsDone(split[1]);
            break;

        case "event":
            output = tasks.addNewEvent(Arrays.copyOfRange(split, 1, split.length));
            break;

        case "find":
            output = tasks.findTasks(split[1]);
            break;

        case "list":
            output = tasks.listAllTasks();
            break;

        case "todo":
            output = tasks.addNewToDo(split[1]);
            break;

        default:
            throw new DukeException("Oops! I am sorry but I don't understand what that means");
        }

        return output;
    }
}
