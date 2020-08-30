import java.time.format.DateTimeFormatter;

import java.util.Scanner;

public class Parser {
    public static final DateTimeFormatter DATE_INPUT_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    public static final DateTimeFormatter DATE_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("MMM dd yyyy");
    public static final DateTimeFormatter TIME_OUTPUT_FORMAT = DateTimeFormatter.ofPattern("h.mma");

    /**
     * Executes the relevant functions based on the given user command.
     */
    public void parseCommands(TaskList tasks, String command, Scanner sc) throws DukeException {
        switch (command) {
        case "bye":
            break;

        case "deadline":
            tasks.addNewDeadline(sc);
            break;

        case "delete":
            tasks.deleteTask(sc);
            break;

        case "done":
            tasks.markTaskAsDone(sc);
            break;

        case "event":
            tasks.addNewEvent(sc);
            break;

        case "find":
            tasks.findTasks(sc);
            break;

        case "list":
           tasks.listAllTasks();
            break;

        case "todo":
            tasks.addNewToDo(sc);
            break;

        default:
            throw new DukeException("Oops! I am sorry but I don't understand what that means");
        }
    }
}
