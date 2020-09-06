package duke;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.RecurringCommand;
import duke.command.ToDoCommand;
import duke.command.UndoCommand;
import duke.exception.DukeFrequencyNotFoundException;
import duke.exception.DukeInputNotRecognizedException;
import duke.exception.DukeTaskNotFoundException;

/**
 * Parses the input by the user.
 */
public class Parser {

    enum Input {
        LIST, BYE, DONE, TODO, DEADLINE, EVENT, DELETE, UNDO, FIND, RECURRING
    }

    /**
     * Parses the input by the user and return a Command.
     *
     * @param fullCommand User input.
     * @return Command.
     * @throws DukeInputNotRecognizedException If the input is not recognised.
     * @throws DukeTaskNotFoundException       If the task is not found.
     */
    public static Command parse(String fullCommand)
            throws DukeInputNotRecognizedException, DukeTaskNotFoundException, DukeFrequencyNotFoundException {
        String[] command = fullCommand.split(" ", 2);
        if (!hasInputs(command[0])) {
            throw new DukeInputNotRecognizedException(" ERROR... INPUT NOT RECOGNIZED. \n PLEASE TRY AGAIN. ");
        }
        switch (Input.valueOf(command[0].toUpperCase())) {
        case LIST:
            return parseList();
        case BYE:
            return parseBye();
        case DONE:
            return parseDone(command);
        case TODO:
            return parseToDo(command);
        case DEADLINE:
            return parseDeadline(command);
        case EVENT:
            return parseEvent(command);
        case DELETE:
            return parseDelete(command);
        case UNDO:
            return parseUndo(command);
        case FIND:
            return parseFind(command);
        case RECURRING:
            return parseRecurring(command);
        default:
            throw new DukeInputNotRecognizedException(" ERROR... INPUT NOT RECOGNIZED. \n PLEASE TRY AGAIN. ");
        }
    }

    /**
     * Checks if string is inside enum Input.
     *
     * @param input User input.
     * @return Boolean value.
     */
    private static boolean hasInputs(String input) {
        for (Input i : Input.values()) {
            if (input.toUpperCase().equals(i.toString())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Handles the case where the user input is "done".
     *
     * @param commandDetails Command inputted by user.
     * @return DoneCommand.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    public static DoneCommand parseDone(String[] commandDetails) throws DukeTaskNotFoundException {
        if (commandDetails.length < 2 || !Character.isDigit(commandDetails[1].charAt(0))) {
            throw new DukeTaskNotFoundException(" ERROR... NON-INTEGER RECOGNIZED OR TASK NUMBER NOT INPUTTED. \n "
                    + "PLEASE TRY AGAIN. ");
        }
        return new DoneCommand(commandDetails);
    }

    /**
     * Handles the case where the user input is "todo".
     *
     * @param commandDetails Command inputted by user.
     * @return ToDoCommand.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    public static ToDoCommand parseToDo(String[] commandDetails) throws DukeTaskNotFoundException {
        if (commandDetails.length < 2) {
            throw new DukeTaskNotFoundException(" ERROR... TODO DESCRIPTION EMPTY. \n PLEASE TRY AGAIN. ");
        }
        return new ToDoCommand(commandDetails);
    }

    /**
     * Handles the case where the user input is "deadline".
     *
     * @param commandDetails Command inputted by user.
     * @return DeadlineCommand.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    public static DeadlineCommand parseDeadline(String[] commandDetails) throws DukeTaskNotFoundException {
        if (commandDetails.length < 2) {
            throw new DukeTaskNotFoundException(" ERROR... DEADLINE DESCRIPTION EMPTY . \n PLEASE TRY AGAIN. ");
        }
        String[] stringArray = commandDetails[1].split("/", 2);
        if (stringArray.length < 2 || stringArray[1].split(" ", 2).length < 2) {
            throw new DukeTaskNotFoundException(" ERROR... DEADLINE DATE EMPTY. \n PLEASE TRY AGAIN. ");
        }
        return new DeadlineCommand(stringArray);
    }

    /**
     * Handles the case where the user input is "event".
     *
     * @param commandDetails Command inputted by user.
     * @return EventCommand.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    public static EventCommand parseEvent(String[] commandDetails) throws DukeTaskNotFoundException {
        if (commandDetails.length < 2) {
            throw new DukeTaskNotFoundException(" ERROR... EVENT DESCRIPTION EMPTY. \n PLEASE TRY AGAIN. ");
        }
        String[] stringArray = commandDetails[1].split("/", 2);
        if (stringArray.length < 2 || stringArray[1].split(" ", 2).length < 1) {
            throw new DukeTaskNotFoundException(" ERROR... EVENT DATE EMPTY. \n PLEASE TRY AGAIN. ");
        }
        return new EventCommand(stringArray);
    }

    /**
     * Handles the case where the user input is "delete".
     *
     * @param commandDetails Command inputted by user.
     * @return DeleteCommand.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    public static DeleteCommand parseDelete(String[] commandDetails) throws DukeTaskNotFoundException {
        if (commandDetails.length < 2 || !Character.isDigit(commandDetails[1].charAt(0))) {
            throw new DukeTaskNotFoundException(" ERROR... NON-INTEGER RECOGNIZED OR TASK NUMBER NOT INPUTTED. \n "
                    + "PLEASE TRY AGAIN. ");
        }
        return new DeleteCommand(commandDetails);
    }

    /**
     * Handles the case where the user input is "undo".
     *
     * @param commandDetails Command inputted by user.
     * @return UndoCommand.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    public static UndoCommand parseUndo(String[] commandDetails) throws DukeTaskNotFoundException {
        if (commandDetails.length < 2 || !Character.isDigit(commandDetails[1].charAt(0))) {
            throw new DukeTaskNotFoundException(" ERROR... NON-INTEGER RECOGNIZED OR TASK NUMBER NOT INPUTTED. \n "
                    + "PLEASE TRY AGAIN. ");
        }
        return new UndoCommand(commandDetails);
    }

    /**
     * Handles the case where the user input is "find".
     *
     * @param commandDetails Command inputted by user.
     * @return FindCommand.
     * @throws DukeTaskNotFoundException If not task is inputted.
     */
    public static FindCommand parseFind(String[] commandDetails) throws DukeTaskNotFoundException {
        if (commandDetails.length < 2) {
            throw new DukeTaskNotFoundException(" ERROR... TODO DESCRIPTION EMPTY. \n PLEASE TRY AGAIN. ");
        }
        return new FindCommand(commandDetails[1].trim());
    }

    /**
     * Handles the case where the user input is "list".
     *
     * @return ListCommand.
     */
    public static ListCommand parseList() {
        return new ListCommand();
    }

    /**
     * Handles the case where the user input is "bye".
     *
     * @return ByeCommand.
     */
    public static ByeCommand parseBye() {
        return new ByeCommand();
    }

    /**
     * Handles the case where the user input is "recurring".
     *
     * @param commandDetails Command inputted by user.
     * @return RecurringCommand.
     * @throws DukeTaskNotFoundException If not task is inputted.
     */
    public static RecurringCommand parseRecurring(String[] commandDetails)
            throws DukeFrequencyNotFoundException, DukeTaskNotFoundException {
        if (commandDetails.length < 2) {
            throw new DukeFrequencyNotFoundException(" ERROR... RECURRING TASK FREQUENCY EMPTY. "
                    + "\n PLEASE TRY AGAIN IN THE FORMAT 'RECURRING [FREQUENCY] [DESCRIPTION]'");
        }
        String[] stringArray = commandDetails[1].split(" ", 2);
        if (stringArray.length < 2) {
            throw new DukeTaskNotFoundException(" ERROR... RECURRING TASK DESCRIPTION EMPTY. "
                    + "\n PLEASE TRY AGAIN IN THE FORMAT 'RECURRING [FREQUENCY] [DESCRIPTION]'");
        }
        return new RecurringCommand(stringArray);
    }
}
