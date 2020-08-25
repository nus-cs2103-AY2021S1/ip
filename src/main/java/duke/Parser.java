package duke;

import duke.command.*;
import duke.exception.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Takes the user's command and parse it
 */
public class Parser {

    protected static List<String> inputFormat = Arrays.asList("dd/MM/yyyy HHmm", "yyyy-mm-dd Haaa");
    protected static SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd yyyy h:mma");

    /**
     * Converts the input date to another date format, if it exist
     * @param str users date input
     * @return String representation of the new date format or the original date input
     */
    public static String dateParser(String str) {
        Date date = null;
        for (String input : inputFormat) {
            try {
                date = new SimpleDateFormat(input).parse(str);
            } catch (ParseException ignore) { }
        }
        if (date == null) {
            return str;
        } else {
            return outputFormat.format(date);
        }
    }

    /**
     * Returns a command object based on the users' input
     *
     * @param input users' input
     * @return Command to be executed
     * @throws DukeException exception to be thrown if there is any parsing error
     */
    public static Command parse(String input) throws DukeException {

        switch (input) {
            case "bye":
                return new ByeCommand();
            case "list":
                return new ListCommand();
        }

        String[] inputArray = input.split(" ", 2);

        if (inputArray.length == 1 && (inputArray[0].equals("done") || inputArray[0].equals("delete") || inputArray[0].equals("todo")
                || inputArray[0].equals("deadline") || inputArray[0].equals("event") || inputArray[0].equals("find"))) {
            throw new EmptyDescriptionException(("The description of " + inputArray[0] + " cannot be empty. Please re-enter"));
        }

        switch (inputArray[0]) {
            case "done":
                return new DoneCommand(Integer.parseInt(inputArray[1]));
            case "delete":
                return new DeleteCommand(Integer.parseInt(inputArray[1]));
            case "find":
                return new FindCommand(inputArray[1]);
            case "todo":
                return new TodoCommand(inputArray[1]);
            case "deadline": {
                formatChecker(inputArray);
                String str = inputArray[1].split(" /", 2)[0];
                String date = inputArray[1].split("/", 2)[1].split(" ", 2)[1];
                return new DeadlineCommand(str, dateParser(date));
            }
            case "event": {
                formatChecker(inputArray);
                String str = inputArray[1].split(" /", 2)[0];
                String date = inputArray[1].split("/", 2)[1].split(" ", 2)[1];
                return new EventCommand(str, dateParser(date));
            }
            default:
                throw new CommandException("Im sorry, I do not understand what you mean. Please re-enter:");
        }
    }

    /**
     * Checks if there is any format discrepancy
     *
     * @param input users' input
     * @throws DukeException exception to be thrown if there is an incorrect format
     */
    private static void formatChecker(String[] input) throws DukeException {
        try {
            String str = input[1].split(" /", 2)[1];
            if(input[0].equals("deadline")) {
                if (!input[1].split("/", 2)[1].split(" " ,2)[0].equals("by")) {
                    throw new WrongFormatException("Incorrect format, Please re-enter");
                }
            } else {
                if (!input[1].split("/", 2)[1].split(" ", 2)[0].equals("at")) {
                    throw new WrongFormatException("Incorrect format, Please re-enter");
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new WrongFormatException("Incorrect format, Please re-enter:");
        }
    }


}
