/**
 * Parser class that deals with loading tasks from the file and
 * saving tasks in the file.
 */
public class Parser {

    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;

    /**
     * Constructor that creates a Parser object
     * @param ui Ui that handles user interactions
     * @param taskList list of tasks
     * @param storage storage object that handles the reading and writing process
     */
    public Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Split the commands into the arguments
     * @param input input that the user puts in
     * @return Array containing the arguments
     */
    public static String[] splitCommand(String input) {
        return input.split(" ", 2);
    }

    /**
     * Split the deadline command into its arguments
     * @param input input that the user puts in
     * @return Array containing the description of the deadline and the time
     */
    public static String[] splitDeadlineArguments(String input) {
        return input.split(" /by ", 2);
    }

    /**
     * Split the Event command into its arguments
     * @param input input that the user puts in
     * @return Array containing the description of the event and the time
     */
    public static String[] splitEventArguments(String input) {
        return input.split(" /at ", 2);
    }
    /**
     * Evaluates the syntax of the input command and executes it,
     * throwing a DukeException if the syntax is correct.
     * @param input the user input
     * @return String containing message corresponding to action
     */
    public Command parse(String input) throws DukeException {
        String[] commandArguments = splitCommand(input);
        String command = commandArguments[0];
        String args;
        if (commandArguments.length < 2) {
            args = null;
        } else {
            args = commandArguments[1];
        }
        switch (command) {
            case "bye":
                return new ByeCommand(ui, taskList, args);
            case "list":
                return new ListCommand(ui, taskList, args);
            case "done":
                return new DoneCommand(ui, taskList, args);
            case "deadline":
                return new DeadlineCommand(ui, taskList, args);
            case "event":
                return new EventCommand(ui, taskList, args);
            case "todo":
                return new TodoCommand(ui, taskList, args);
            case "delete":
                return new DeleteCommand(ui, taskList, args);
            case "find":
                return new FindCommand(ui, taskList, args);
            default:
                throw new DukeException("Sorry sis! I don't know what they means!");
        }
    }


}
