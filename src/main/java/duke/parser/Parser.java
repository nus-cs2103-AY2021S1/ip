package duke.parser;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.command.TodoCommand;

/**
 * Contains two parse functions, one for interpreting user input
 * to get the correct Command used, and another function to separate
 * strings with time into its description and time.
 */
public class Parser {

    /**
     * Given the full input string, this method checks the first
     * few characters to see if the keyword corresponds to a specific
     * Command and returns a new command to be executed.
     *
     * @param fullCommand The entire command from the user input.
     * @return The Command corresponding to the keyword from the user.
     */
    public static Command getCommand(String fullCommand, TaskList taskList, Storage storage) {
        String[] splitWords = fullCommand.split(" ");
        String keyword = splitWords[0].toLowerCase();
        switch (keyword) {
        case "bye":
            return new ByeCommand(storage, taskList);
        case "list":
            return new ListCommand(taskList);
        case "done":
            return new DoneCommand(fullCommand, taskList);
        case "delete":
            return new DeleteCommand(fullCommand, taskList);
        case "todo":
            return new TodoCommand(fullCommand, taskList);
        case "event":
            return new EventCommand(fullCommand, taskList);
        case "deadline":
            return new DeadlineCommand(fullCommand, taskList);
        case "find":
            return new FindCommand(fullCommand, taskList);
        case "help":
            return new HelpCommand(fullCommand);
        default:
            return new InvalidCommand();
        }
    }

    /**
     * Helper function to process a timed task description (task.Event, task.Deadline) and
     * split it into 2 strings, the description and the task date.
     *
     * @param str The raw string to be processed.
     * @return A String array of size 2. Index 0 contains the task's description,
     * index 1 contains the date of the task.
     */
    public static String[] parseTimedTask(String str) throws DukeException {
        String[] result = new String[2];
        // Split the string first then loop through to find the stop point at either /at or /by.
        String[] arr = str.split(" ");
        int indexToStop = -1;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals("/at") || arr[i].equals("/by")) {
                indexToStop = i;
                // Stop index when indicator /at or /by is found.
            }
        }
        if (indexToStop == -1) {
            throw new DukeException("Incorrect Input for timed task.");
        }

        // Use StringBuilder Class to recreate the description and time separately.
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < indexToStop; j++) {
            sb.append(arr[j]);
            if (j != indexToStop - 1) {
                sb.append(" ");
            }
        }
        // task.Event / task.Deadline description has been built, pass it to result[0].
        result[0] = sb.toString();

        sb = new StringBuilder();
        for (int k = indexToStop + 1; k < arr.length; k++) {
            sb.append(arr[k]);
            if (k != arr.length - 1) {
                sb.append(" ");
            }
        }
        String date = sb.toString();
        // Now to check if this date can be formatted nicely using DateTimeProcessor class.
        String parsedDate = new DateTimeProcessor().getParsedDate(date);
        result[1] = parsedDate;
        return result;
    }
}
