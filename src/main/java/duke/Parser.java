package duke;

/**
 * Parser class will take in user's inputs and make sense of it.
 */
public class Parser {
    private static TaskList tasks;

    /**
     * Interprets the command type from user's input and calls CommandHandler to process the commands.
     *
     * @param input
     * @throws DukeException
     */
    public static String parseInputs(String input) throws DukeException {
        assert !input.isEmpty() : "Input shouldn't be empty!";
        String output = "";
        if (input.equals("bye")) {
            output += CommandHandler.handleCommands(input, DukeCommandType.EXIT, tasks);
        } else if (input.equals("help")) {
            output += CommandHandler.handleCommands(input, DukeCommandType.HELP, tasks);
        } else if (input.equals("list")) {
            output += CommandHandler.handleCommands(input, DukeCommandType.LIST, tasks);
        } else if (input.startsWith("todo")) {
            output += CommandHandler.handleCommands(input, DukeCommandType.TODO, tasks);
        } else if (input.startsWith("deadline")) {
            output += CommandHandler.handleCommands(input, DukeCommandType.DEADLINE, tasks);
        } else if (input.startsWith("event")) {
            output += CommandHandler.handleCommands(input, DukeCommandType.EVENT, tasks);
        } else if (input.startsWith("done")) {
            output += CommandHandler.handleCommands(input, DukeCommandType.DONE, tasks);
        } else if (input.startsWith("delete")) {
            output += CommandHandler.handleCommands(input, DukeCommandType.DELETE, tasks);
        } else if (input.startsWith("find")) {
            output += CommandHandler.handleCommands(input, DukeCommandType.FIND, tasks);
        } else {
            output += CommandHandler.handleCommands(input, DukeCommandType.UNKNOWN, tasks);
        }
        assert !output.equals(null) : "Output should not be null.";
        return output;
    }
}
