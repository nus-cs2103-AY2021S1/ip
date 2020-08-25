package duke.parser;

import duke.command.*;
import duke.exception.DukeException;
import duke.exception.UnknownCommandException;
import duke.task.TaskType;

/**
 * Parses the user input and creating a command from it.
 */
public class Parser {

    /**
     * Parses the user input and returns the corresponding command.
     *
     * @param text User text input.
     * @return Corresponding command.
     * @throws DukeException If user input does not belong to the list of known commands.
     */
    public static Command parse(String text) throws DukeException {
        String[] inputArr = deconstruct(text);
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
        } else if (keyWord.equals("bye")) {
            return new ExitCommand();
        } else {
            throw new UnknownCommandException();
        }
    }

    private static String[] deconstruct(String input) {
        String formattedString = input.trim().replaceAll("\\s{2,}", " ");
        return formattedString.split(" ", 2);
    }

    private static String getKeyWord(String[] arr) {
        return arr[0].toLowerCase();
    }

    private static String getRestOfWord(String[] arr) {
        return arr.length == 1 ? "" : arr[1];
    }

}
