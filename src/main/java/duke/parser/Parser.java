package duke.parser;

import duke.command.AddComplexTaskCommand;
import duke.command.AddToDoCommand;
import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ShowCommand;
import duke.command.SimpleCommand;
import duke.command.SimpleCommandType;
import duke.exception.DukeException;
import duke.exception.UnknownCommandException;
import duke.task.TaskType;

/**
 * Parses the user input and creating a command from it.
 */
public class Parser {

    private static final String INVALID_ARR_ERROR = "Array is not empty";
    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param text User text input.
     * @return Corresponding command.
     * @throws DukeException If user input does not belong to the list of known commands.
     */
    public static Command parse(String text) throws DukeException {
        String[] inputArr = deconstructInput(text);
        String keyWord = getKeyWord(inputArr);
        String details = getRestOfWord(inputArr);

        if (keyWord.equals("list")) {
            return new ShowCommand();
        } else if (keyWord.equals("done")) {
            return new SimpleCommand(details, SimpleCommandType.DONE);
        } else if (keyWord.equals("delete")) {
            return new SimpleCommand(details, SimpleCommandType.DELETE);
        } else if (keyWord.equals("todo")) {
            return new AddToDoCommand(details);
        } else if (keyWord.equals("deadline")) {
            return new AddComplexTaskCommand(details, TaskType.DEADLINE);
        } else if (keyWord.equals("event")) {
            return new AddComplexTaskCommand(details, TaskType.EVENT);
        } else if (keyWord.equals("find")) {
            return new FindCommand(details);
        } else if (keyWord.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Deconstructs the string into a string array.
     *
     * @param input Input String.
     * @return String array of the input.
     */
    private static String[] deconstructInput(String input) {
        String formattedString = input.trim().replaceAll("\\s{2,}", " ");
        return formattedString.split(" ", 2);
    }

    /**
     * Obtains the keyword of the user input.
     *
     * @param arr User input in a String array.
     * @return Keyword.
     */
    private static String getKeyWord(String[] arr) {
        assert (arr.length > 0) : INVALID_ARR_ERROR;
        return arr[0].toLowerCase();
    }

    /**
     * Obtains the rest of the user input.
     *
     * @param arr User input in a String array.
     * @return Rest of the user input.
     */
    private static String getRestOfWord(String[] arr) {
        assert (arr.length > 0) : INVALID_ARR_ERROR;
        return arr.length == 1 ? "" : arr[1];
    }
}
