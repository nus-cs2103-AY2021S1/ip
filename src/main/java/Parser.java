import java.util.List;

/**
 * Parser class deals with making sense of the user command
 * It has functions that will help in parsing user commands
 */
public class Parser {
    /**
     * Splits what the user types in to 2 parts
     * @param command
     * @return a 2 sized String array that is related to a command
     */
    public static String[] parse(String command) {
        return command.split(" ", 2);
    }

    /**
     * Gets the task index of a task list using a command, something like "<command> <number>"
     * @param commands
     * @param taskList
     * @return a number related to the task list
     * @throws DukeException
     */
    public static int getTaskIndex(String[] commands, List<Task> taskList) throws DukeException {
        if (commands.length < 2) {
            throw new DukeException("Please put a number to select a task for the \""
                   + commands[0] + "\" action!");
        }
        try {
            int index = Integer.parseInt(commands[1]);
            Task task = taskList.get(index - 1);
            return index - 1;
        }  catch (NumberFormatException nfe) {
            throw new DukeException("Please input an actual number e.g. 1, 2, 3.");
        } catch (IndexOutOfBoundsException iobe) {
            throw new DukeException("Please select a valid task.");
        }
    }

    /**
     * Checks if the add command is valid, throws a duke exception if it is not
     * @param commands
     * @throws DukeException
     */
    public static void checkValidAddCommand(String[] commands) throws DukeException {
        if (commands.length < 2) {
            throw new DukeException("The description of a " + commands[0] + " cannot be empty.");
        }
    }
}
