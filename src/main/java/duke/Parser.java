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

public class Parser {

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

    public static boolean isValidIndex(String input, int listSize) {
        String[] stringArray = input.split(" ");
        int index;
        try {
            index = Integer.parseInt(stringArray[1]);
        } catch(Exception e) {
            return false;
        }
        return index <= listSize && index > 0;
    }

    public static int getIndex(String input) {
        String[] stringArray = input.split(" ");
        return Integer.parseInt(stringArray[1]) - 1;
    }

    public static String getTodoDescription(String input) throws DukeException{
        try {
            return input.substring(5);
        } catch (Exception e){
            throw new DukeException("Todo cannot be empty!");
        }
    }

    public static String[] getDeadlineStrings(String input) {
        String[] stringArray = input.split(" /by ");
        stringArray[0] = stringArray[0].substring(9);
        return stringArray;
    }

    public static String[] getEventTimeStrings(String input) {
        String[] stringArray = input.split(" /at ");
        stringArray[0] = stringArray[0].substring(6);
        return stringArray;
    }

    public static LocalDate parseDate(String date) {
        return LocalDate.parse(date);
    }

}
