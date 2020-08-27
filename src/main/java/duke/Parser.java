package duke;

import duke.command.Command;
import duke.command.ExitCommand;
import duke.command.AddCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.InputCommand;
import duke.command.GetCommand;
import duke.command.ListCommand;

import java.time.LocalDate;

/**
 * Represents a parser that parses the input of users.
 */
public class Parser {

    /**
     * Parses the user's input.
     * @param fullCommand String command by user.
     * @return Command object.
     * @throws DukeException If invalid input by user.
     */
    public static Command parse(String fullCommand) throws DukeException {

        String input = fullCommand;
        String command = input.split(" ")[0];
        InputCommand inputCommand;

        try {
            inputCommand = InputCommand.valueOf(command.toUpperCase());
        } catch (Exception e) {
            inputCommand = InputCommand.INVALID;
        }

        switch (inputCommand) {
        case BYE:
            return new ExitCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            return new DoneCommand(input);
        case DELETE:
            return new DeleteCommand(input);
        case TODO:
        case DEADLINE:
        case EVENT:
            return new AddCommand(input, inputCommand);
        case GET_TASK:
            return new GetCommand(input);
        default:
            throw new DukeException("Give me a valid banana (input)!");
        }

    }

    /**
     * Checks if the input has valid index.
     * @param input String input of user.
     * @param tasksNumber Number of tasks.
     * @return true if valid, false if invalid.
     */
    public static boolean isValidIndex(String input, int tasksNumber) {
        String[] stringArray = input.split(" ");
        int index;
        try {
            index = Integer.parseInt(stringArray[1]);
        } catch(Exception e) {
            return false;
        }
        return index <= tasksNumber && index > 0;
    }

    /**
     * Gets index of input.
     * @param input String input of user.
     * @return Index in input.
     */
    public static int getIndex(String input) {
        String[] stringArray = input.split(" ");
        return Integer.parseInt(stringArray[1]) - 1;
    }

    /**
     * Gets description of Todo object.
     * @param input String input of user.
     * @return String description of Todo object.
     * @throws DukeException if Todo description is empty.
     */
    public static String getTodoDescription(String input) throws DukeException{
        try {
            return input.substring(5);
        } catch (Exception e){
            throw new DukeException("Todo cannot be empty!");
        }
    }

    /**
     * Gets array of deadline description strings.
     * @param input String input of user.
     * @return Array of deadline description strings.
     */
    public static String[] getDeadlineStrings(String input) {
        String[] stringArray = input.split(" /by ");
        stringArray[0] = stringArray[0].substring(9);
        return stringArray;
    }

    /**
     * Gets array of event description strings.
     * @param input String input of user.
     * @return Array of event description strings.
     */
    public static String[] getEventTimeStrings(String input) {
        String[] stringArray = input.split(" /at ");
        stringArray[0] = stringArray[0].substring(6);
        return stringArray;
    }

    /**
     * Parses string input of user and returns LocalDate object.
     * @param date String input of user.
     * @return LocalDate object corresponding to input of user.
     */
    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

}
