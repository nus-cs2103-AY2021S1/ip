import java.util.List;

/**
 * Parser class deals with making sense of the user command
 * It has functions that will help in parsing user commands
 */
public class Parser {
    private static final String DELIMITER = " ";
    private static final int MAX_COMMAND_LENGTH = 2;

    /**
     * Splits what the user types in to 2 parts
     * @param command
     * @return a 2 sized String array that is related to a command
     */
    public static String[] parse(String command) {
        return command.split(DELIMITER, MAX_COMMAND_LENGTH);
    }

    /**
     * Gets the task index of a task list using a command, something like "{command} {number}"
     * @param commands
     * @param taskList
     * @return a number related to the task list
     * @throws DukeException
     */
    public static int getTaskIndex(String[] commands, List<Task> taskList) throws DukeException {
        if (commands.length < MAX_COMMAND_LENGTH) {
            throw new DukeException("Please put a number to select a task for the \""
                   + commands[0] + "\" action!");
        }
        int index;
        try {
            index = Integer.parseInt(commands[1]);
            taskList.get(index - 1);
        } catch (NumberFormatException numberFormatException) {
            throw new DukeException("Please input an actual number e.g. 1, 2, 3.");
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            throw new DukeException("Please select a valid task.");
        }

        return index - 1;
    }

    /**
     * Checks if the add command is valid, throws a duke exception if it is not
     * @param commands
     * @throws DukeException
     */
    public static void checkValidAddCommand(String[] commands) throws DukeException {
        if (commands.length < MAX_COMMAND_LENGTH) {
            throw new DukeException("The description of a " + commands[0] + " cannot be empty.");
        }
    }
}
