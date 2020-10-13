package duke;

/**
 * Parser helps to handle the user input and classify them into a specific Command.
 */
public class Parser {

    public static final String SPACE = " ";
    public static final String EMPTY_STRING = "";
    public static final String COMMA = ",";

    /**
     * Checks if the Input is a termination command.
     *
     * @param input Input of the user.
     * @return True if input is a termination command.
     */
    public static boolean isBye(String input) {
        String firstWord = input.split(SPACE, 2)[0];
        return firstWord.equals("bye");
    }

    /**
     * Parses the userInput and returns a specific command based on the input.
     *
     * @param input    Input of the user.
     * @param taskList TaskList of the program.
     * @return Specific Command related to the user input.
     */
    public static Command parseInput(String input, TaskList taskList) {
        Command command;

        String firstWord = input.split(SPACE, 2)[0];

        if (firstWord.equals("list")) {
            command = new ListCommand(taskList);
        } else if (firstWord.equals("find")) {
            command = new FindCommand(taskList, input);
        } else if (firstWord.equals("done")) {
            command = new DoneCommand(taskList, input);
        } else if (firstWord.equals("delete")) {
            command = new DeleteCommand(taskList, input);
        } else if (firstWord.equals("todo")) {
            command = new AddCommand(Command.CREATE_TODO, taskList, input);
        } else if (firstWord.equals("deadline")) {
            command = new AddCommand(Command.CREATE_DEADLINE, taskList, input);
        } else if (firstWord.equals("event")) {
            command = new AddCommand(Command.CREATE_EVENT, taskList, input);
        } else {
            command = new InvalidCommand();
        }
        return command;
    }
}
