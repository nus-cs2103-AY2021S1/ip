package duke;

/**
 * Encapsulates the parsing of user's input.
 */
public class Parser {

    // Error messages
    private static final String ERR_UNKNOWN_COMMAND = "I'm sorry, but I don't know what that means.\n";
    private static final String ERR_BYE_WITH_USER_ACTION = "Do you mean \"bye\"?\n";
    private static final String ERR_LIST_WITH_USER_ACTION = "Do you mean \"list\"?\n";
    private static final String ERR_DELETE_WITHOUT_USER_ACTION = "Which task do you want to delete?\n";
    private static final String ERR_DONE_WITHOUT_USER_ACTION = "Which task have you done?\n";
    private static final String ERR_GET_WITHOUT_USER_ACTION = "Which date you want to get tasks from?\n";
    private static final String ERR_FIND_WITHOUT_USER_ACTION = "Include a search term.\n";
    private static final String ERR_TODO_WITHOUT_USER_ACTION = "The description of a todo cannot be empty.\n";
    private static final String ERR_EVENT_WITHOUT_USER_ACTION = "The description of an event cannot be empty.\n";
    private static final String ERR_DEADLINE_WITHOUT_USER_ACTION = "The description of a deadline cannot be empty.\n";

    private final Storage storage;

    /**
     * Creates a parser with a storage for reading and writing data.
     *
     * @param storage Storage for reading and writing data.
     */
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
        assert(splitInput[0] != null);
        String userCommand = splitInput[0];
        Command command = this.extractCommandType(userCommand);

        try {
            String userAction = "";

            // Extract user action if command is not BYE or LIST
            // BYE and LIST commands have no user action
            if (!userInput.equals("bye") && !userInput.equals("list")) {
                assert(splitInput[1] != null);
                userAction = splitInput[1];

                // Check if user action is present for BYE and LIST commands
                if (command == Command.BYE) {
                    throw new DukeInputException(Parser.ERR_BYE_WITH_USER_ACTION);
                }
                if (command == Command.LIST) {
                    throw new DukeInputException(Parser.ERR_LIST_WITH_USER_ACTION);
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
            throw new DukeInputException(Parser.ERR_UNKNOWN_COMMAND);
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
            throw new DukeInputException(Parser.ERR_DELETE_WITHOUT_USER_ACTION);
        case DONE:
            throw new DukeInputException(Parser.ERR_DONE_WITHOUT_USER_ACTION);
        case GET:
            throw new DukeInputException(Parser.ERR_GET_WITHOUT_USER_ACTION);
        case FIND:
            throw new DukeInputException(Parser.ERR_FIND_WITHOUT_USER_ACTION);
        case TODO:
            throw new DukeInputException(Parser.ERR_TODO_WITHOUT_USER_ACTION);
        case EVENT:
            throw new DukeInputException(Parser.ERR_EVENT_WITHOUT_USER_ACTION);
        case DEADLINE:
            throw new DukeInputException(Parser.ERR_DEADLINE_WITHOUT_USER_ACTION);
        default:
            break;
        }
    }
}
