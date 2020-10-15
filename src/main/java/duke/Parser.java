package duke;

/**
 * The Parser class deals with making sense of the user command.
 */
public class Parser {

    /**
     * Returns a reminder command.
     *
     * @return The reminder command to execute.
     */
    public static Command giveReminders() {
        return new ReminderCommand();
    }
    
    /**
     * Reads the user input and determines which command to execute.
     *
     * @param input User input.
     * @return Command The type of command to execute.
     * @throws DukeException
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArray = input.split(" ", 2);
        String typeOfCommand = inputArray[0];
 
        switch (typeOfCommand) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "done":
            return new CompleteCommand(input);
        case "delete":
            return new DeleteCommand(input);
        case "find":
            return new FindCommand(input);
        case "todo":
            return new ToDoCommand(input);
        case "deadline":
            return new DeadlineCommand(input);
        case "event":
            return new EventCommand(input);
        default:
            return new InvalidCommand();
        }
    }
}
