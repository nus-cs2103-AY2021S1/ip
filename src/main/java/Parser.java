/**
 * Represents a Parser object.
 * Handles the user input commands and executes them.
 */
public class Parser {
    private static final int START = 0;
    private static final int MINIMUM_COMMAND_LENGTH = 3;
    private static final int END_FOUR_CHARACTERS = 4;
    private static final int END_FIVE_CHARACTERS = 5;
    private static final int END_SIX_CHARACTERS = 6;
    private static final int END_SEVEN_CHARACTERS = 7;
    private static final int END_NINE_CHARACTERS = 9;

    private static final String COMMAND_SEPARATOR = " ";
    private static final String PARSE_BYE = "bye";
    private static final String PARSE_LIST = "list";
    private static final String PARSE_DONE = "done ";
    private static final String PARSE_TODO = "todo ";
    private static final String PARSE_FIND = "find ";
    private static final String PARSE_EVENT = "event ";
    private static final String PARSE_DELETE = "delete ";
    private static final String PARSE_DEADLINE = "deadline ";

    private TaskList taskManager;

    /**
     * Creates a Parser object.
     * It is used for identifying String inputs from user.
     *
     * @param taskManager TaskList object, required to interact with it.
     */
    public Parser(TaskList taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Processes user input for classification and execution.
     *
     * @param str is the line of String obtained from user input when user is using the JavaFX GUI.
     *
     * Reads and makes sense of user input commands which includes:
     * (bye, list, todo, deadline, event, delete)
     *
     * It changes the output to a String as Duke Response.
     *
     * @return a String representation of Duke output based on user commands.
     *
     * @throws DukeException when command is wrong, unidentifiable or missing.
     */
    public String parseUserInput(String str) throws DukeException {
        String trimmedStr = str.trim();
        int commandSpace = str.indexOf(COMMAND_SEPARATOR);

        if (trimmedStr.length() < MINIMUM_COMMAND_LENGTH) {
            ExceptionCommand exceptionCommand  = new ExceptionCommand(ExceptionCommand.INVALID_COMMAND);
            throw new DukeException(exceptionCommand.getOutput());
        }

        assert trimmedStr.length() > 2 : "user input should not be empty/just white spaces";

        if (str.equals(PARSE_BYE)) {
            ByeCommand byeCommand = new ByeCommand();
            return byeCommand.getOutput();

        } else if (str.equals(PARSE_LIST)) {
            ListCommand listCommand = new ListCommand(taskManager);
            return listCommand.getOutput();

        } else if (commandSpace <= START) { //No space in user input
            ExceptionCommand exceptionCommand  = new ExceptionCommand(ExceptionCommand.INVALID_COMMAND);
            throw new DukeException(exceptionCommand.getOutput());
        }

        assert commandSpace > 0 : "There should be a spacing after command call from this point onwards";

        if (str.substring(START, END_FIVE_CHARACTERS).equals(PARSE_DONE)) {
            DoneCommand doneCommand = new DoneCommand(str, taskManager);
            return doneCommand.getOutput();

        } else if (str.substring(START, END_FIVE_CHARACTERS).equals(PARSE_TODO)) {
            TodoCommand todoCommand = new TodoCommand(str, taskManager);
            return todoCommand.getOutput();

        } else if (str.substring(START, END_FIVE_CHARACTERS).equals(PARSE_FIND)) {
            FindCommand findCommand = new FindCommand(str, taskManager);
            return findCommand.getOutput();

        } else if (commandSpace <= END_FOUR_CHARACTERS) {
            ExceptionCommand exceptionCommand = new ExceptionCommand(ExceptionCommand.INVALID_COMMAND);
            throw new DukeException(exceptionCommand.getOutput());
        }

        assert str.length() > 4 : "The length of command with a space character should be > 4";

        if (str.substring(START, END_SIX_CHARACTERS).equals(PARSE_EVENT)) {
            try {
                EventCommand eventCommand = new EventCommand(str, taskManager);
                return eventCommand.getOutput();

            } catch(DukeException de) {
                ExceptionCommand exceptionCommand = new ExceptionCommand(ExceptionCommand.INVALID_DATE_FORMAT);
                throw new DukeException(exceptionCommand.getOutput());
            }

        } else if (commandSpace <= END_FIVE_CHARACTERS) {
            ExceptionCommand exceptionCommand  = new ExceptionCommand(ExceptionCommand.INVALID_COMMAND);
            throw new DukeException(exceptionCommand.getOutput());
        }

        assert str.length() > 5 : "The length of command with a space character should be > 5";

        if (str.substring(START, END_SEVEN_CHARACTERS).equals(PARSE_DELETE)) {
            DeleteCommand deleteCommand = new DeleteCommand(str, taskManager);
            return deleteCommand.getOutput();

        } else if (commandSpace <= END_SIX_CHARACTERS) {
            ExceptionCommand exceptionCommand  = new ExceptionCommand(ExceptionCommand.INVALID_COMMAND);
            throw new DukeException(exceptionCommand.getOutput());
        }

        assert str.length() > 6 : "The length of command with a space character should be > 6";

        if (str.substring(START, END_NINE_CHARACTERS).equals(PARSE_DEADLINE)) {
            try {
                DeadlineCommand deadlineCommand = new DeadlineCommand(str, taskManager);
                return deadlineCommand.getOutput();

            } catch(DukeException de) {
                ExceptionCommand exceptionCommand = new ExceptionCommand(ExceptionCommand.INVALID_DATE_FORMAT);
                throw new DukeException(exceptionCommand.getOutput());
            }

        } else {
            ExceptionCommand exceptionCommand  = new ExceptionCommand(ExceptionCommand.INVALID_COMMAND);
            throw new DukeException(exceptionCommand.getOutput());
        }
    }
}
