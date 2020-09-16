import java.time.format.DateTimeParseException;

/**
 * Parser class deals with making sense of user input.
 */
public class Parser {
    private final Ui ui;
    private final TaskList taskList;
    private final Storage storage;
    
    Parser(Ui ui, TaskList taskList, Storage storage) {
        this.ui = ui;
        this.taskList = taskList;
        this.storage = storage;
    }

    /**
     * Splits user input into command and instructions.
     * @param input user input into the application.
     * @return String array containing the command in the first index and the
     * instructions to execute in the second index.
     */
    public static String[] splitCommandAndInstructions(String input) {
        return input.split(" ", 2);
    }

    /**
     * Splits deadline arguments into description and time.
     * @param input user input containing both description and time.
     * @return String array containing the description in the first index and the 
     * time in the second index.
     */
    public static String[] splitDeadlineArguments(String input) {
        return input.split(" /by ", 2);
    }

    /**
     * Splits event arguments into description and time.
     * @param input user input containing both description and time.
     * @return String array containing the description in the first index and the 
     * time in the second index.
     */
    public static String[] splitEventArguments(String input) {
        return input.split(" /at ", 2);
    }

    /**
     * Splits time from schedule arguments.
     * @param input user input containing time.
     * @return String array containing schedule time in the second index.
     */
    public static String[] splitScheduleArguments(String input) {
        return input.split(" /on ", 2);
    }
    
    
    /**
     * Parses user input into the application's corresponding command.
     * @param input the user input as a String.
     * @return Command to be executed based on user input.
     */
    public Command parseInputIntoCommand(String input) throws DukeUnknownArgumentException {
        String result;
        String[] commandAndArguments = splitCommandAndInstructions(input);
        String command = commandAndArguments[0];
        String arguments;
        if(commandAndArguments.length < 2) {
            //insufficient arguments
            arguments = null;
        } else {
           arguments = commandAndArguments[1];
        }
        switch (command) {
        case "bye":
            return new ByeCommand(taskList, ui, arguments);
        case "list":
            return new ListCommand(taskList, ui, arguments);
        case "find":
            return new FindCommand(taskList, ui, arguments);
        case "done":
            return new DoneCommand(taskList, ui, arguments);
        case "deadline":
            return new DeadlineCommand(taskList, ui, arguments);
        case "todo":
            return new TodoCommand(taskList, ui, arguments);
        case "delete":
            return new DeleteCommand(taskList, ui, arguments);
        case "schedule":
            return new ScheduleCommand(taskList, ui, arguments);
        default:
            throw new DukeUnknownArgumentException("");
        }
    }
}
