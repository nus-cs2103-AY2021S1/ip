import enums.Command;
import tasks.Deadline;
import tasks.Event;
import tasks.TaskManager;
import tasks.ToDo;
import utils.PrettyPrinter;
import utils.ResourceHandler;
import java.util.Scanner;

/**
 * A read-eval-print loop (REPL) that reads in a command from the user, executes it, and prints out the result.
 */
public class Repl {
    // Formatting
    /** Number of spaces to prefix each line with. */
    private static final int LEFT_PADDING_SIZE = 4;
    /** Number of underscores each divider should be made up of. */
    private static final int DIVIDER_LENGTH = 60;

    /** {@code Scanner} object which reads in user input. */
    private static final Scanner scanner = new Scanner(System.in);
    /** {@code PrettyPrinter} object for formatting the REPL output. */
    private static final PrettyPrinter prettyPrinter = new PrettyPrinter(LEFT_PADDING_SIZE, DIVIDER_LENGTH);
    /** {@code TaskManager} object to keep track of tasks */
    private static final TaskManager taskManager = new TaskManager();

    /**
     * Runs the REPL.
     */
    public static void run() {
        prettyPrinter.print(ResourceHandler.getString("repl.greeting"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String firstToken = line.split(" ")[0];
            Command command;
            String[] args;
            try {
                command = Command.valueOf(firstToken.toUpperCase());
                switch (command) {
                    case BYE:
                        prettyPrinter.print(ResourceHandler.getString("repl.farewell"));
                        return;
                    case DEADLINE:
                        line = line.replaceFirst("^deadline\\s*", "");
                        args = line.split("\\s*/by\\s*");
                        String deadlineName = args[0];
                        String dueDate = args[1];
                        prettyPrinter.print(taskManager.addTask(new Deadline(deadlineName, dueDate)));
                        break;
                    case DONE:
                        try {
                            line = line.replaceFirst("^done\\s*", "");
                            args = line.split("");
                            int listIndex = Integer.parseInt(args[0]) - 1;
                            prettyPrinter.print(taskManager.markAsDone(listIndex));
                        } catch (Exception e) {
                            prettyPrinter.print(ResourceHandler.getString("repl.invalidTaskIndex"));
                        }
                        break;
                    case EVENT:
                        line = line.replaceFirst("^deadline\\s*", "");
                        args = line.split("\\s*/at\\s*");
                        String eventName = args[0];
                        String dateTime = args[1];
                        prettyPrinter.print(taskManager.addTask(new Event(eventName, dateTime)));
                        break;
                    case LIST:
                        prettyPrinter.print(taskManager.toString());
                        break;
                    case TODO:
                        String toDoName = line.replaceFirst("^todo\\s*", "");
                        prettyPrinter.print(taskManager.addTask(new ToDo(toDoName)));
                        break;
                }
            } catch (IllegalArgumentException e) {
                prettyPrinter.print(ResourceHandler.getString("repl.unknownCommand"));
            }
        }
    }
}
