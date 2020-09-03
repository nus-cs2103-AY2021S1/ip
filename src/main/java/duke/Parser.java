package duke;

/**
 * Encapsulates the parsing of user's input.
 */
public class Parser {

    private final Storage storage;

    public Parser(Storage storage) {
        this.storage = storage;
    }

    /**
     * Processes the user input fed to Duke.
     * A user input consists of a user command and a user action.
     * User command is always the first word of the user input.
     * For example, when a user inputs "todo buy apple", the user command is 'todo'
     * and the user action is 'buy apple'.
     *
     * @param userInput User input string.
     * @return String describing the result of the processing.
     * @throws DukeInputException If user command is invalid or user action is missing.
     */
    public String processInput(String userInput) throws DukeInputException {
        // Split the user input into user command and user action
        String[] splitInput = userInput.split(" ", 2);

        // Extract command type
        String userCommand = splitInput[0];
        Command command = this.extractCommandType(userCommand);

        try {
            String userAction = "";

            // Extract user action if command is not BYE or LIST
            // BYE and LIST commands have no user action
            if (!userInput.equals("bye") && !userInput.equals("list")) {
                userAction = splitInput[1];

                // Check if user action is present for BYE and LIST commands
                if (command == Command.BYE) {
                    throw new DukeInputException("Do you mean \"bye\"?\n");
                }
                if (command == Command.LIST) {
                    throw new DukeInputException("Do you mean \"list\"?\n");
                }
            }

            // Execute the command type with user action
            Executor executor = new Executor(this.storage);
            return executor.executeCommand(command, userAction);
        } catch (IndexOutOfBoundsException e) {
            handleMissingUserAction(command);
            return "";
        }
    }

    /**
     * Extracts the command type from a string.
     *
     * @param userCommand String indicating the user command.
     * @return Command type.
     * @throws DukeInputException If user command is invalid.
     */
    private Command extractCommandType(String userCommand) throws DukeInputException {
        switch (userCommand) {
        case "bye":
            return Command.BYE;
        case "list":
            return Command.LIST;
        case "delete":
            return Command.DELETE;
        case "done":
            return Command.DONE;
        case "get":
            return Command.GET;
        case "find":
            return Command.FIND;
        case "todo":
            return Command.TODO;
        case "event":
            return Command.EVENT;
        case "deadline":
            return Command.DEADLINE;
        default: // Unknown command
            throw new DukeInputException("I'm sorry, but I don't know what that means. \u2639\n");
        }
    }

    /**
     * Handles missing user action.
     *
     * @param command Command type.
     * @throws DukeInputException If user action is missing.
     */
    private void handleMissingUserAction(Command command) throws DukeInputException {
        switch (command) {
        case DELETE:
            throw new DukeInputException("Which task do you want to delete?\n");
        case DONE:
            throw new DukeInputException("Which task have you done?\n");
        case GET:
            throw new DukeInputException("Include the date you want to get tasks from.\n");
        case FIND:
            throw new DukeInputException("Include a search term.\n");
        case TODO:
            throw new DukeInputException("The description of a todo cannot be empty.\n");
        case EVENT:
            throw new DukeInputException("The description of an event cannot be empty.\n");
        case DEADLINE:
            throw new DukeInputException("The description of a deadline cannot be empty.\n");
        default:
            break;
        }
    }
}
