package main.java.duke;

import main.java.duke.command.ByeCommand;
import main.java.duke.command.Command;
import main.java.duke.command.DeadlineCommand;
import main.java.duke.command.ListCommand;
import main.java.duke.command.DeleteCommand;
import main.java.duke.command.UndoCommand;
import main.java.duke.command.EventCommand;
import main.java.duke.command.DoneCommand;
import main.java.duke.command.ToDoCommand;
import main.java.duke.command.FindCommand;
import main.java.duke.exception.DukeInputNotRecognizedException;
import main.java.duke.exception.DukeTaskNotFoundException;

/**
 * Parses the input by the user.
 */
public class Parser {

    enum Input {
        LIST, BYE, DONE, TODO, DEADLINE, EVENT, DELETE, UNDO, FIND
    }

    /**
     * Parses the input by the user and return a Command.
     *
     * @param fullCommand User input.
     * @return Command.
     * @throws DukeInputNotRecognizedException If the input is not recognised.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    public static Command parse(String fullCommand) throws DukeInputNotRecognizedException, DukeTaskNotFoundException {
        String[] command = fullCommand.split(" ", 2);
        if (hasInputs(command[0])) {
            switch (Input.valueOf(command[0].toUpperCase())) {
            case LIST:
                return new ListCommand();
            case BYE:
                return new ByeCommand();
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
                return parseFind(command[1].trim());
            }
        } else {
            throw new DukeInputNotRecognizedException(" ERROR... INPUT NOT RECOGNIZED. \n PLEASE TRY AGAIN. ");
        }
        return null;
    }

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
        if (commandDetails.length > 1 && Character.isDigit(commandDetails[1].charAt(0))) {
            return new DoneCommand(commandDetails);
        } else {
            throw new DukeTaskNotFoundException(" ERROR... NON-INTEGER RECOGNIZED OR TASK NUMBER NOT INPUTTED. \n " +
                    "PLEASE TRY AGAIN. ");
        }
    }

    /**
     * Handles the case where the user input is "todo".
     *
     * @param commandDetails Command inputted by user.
     * @return ToDoCommand.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    public static ToDoCommand parseToDo(String[] commandDetails) throws DukeTaskNotFoundException {
        if (commandDetails.length > 1) {
            return new ToDoCommand(commandDetails);
        } else {
            throw new DukeTaskNotFoundException(" ERROR... TODO DESCRIPTION EMPTY. \n PLEASE TRY AGAIN. ");
        }
    }

    /**
     * Handles the case where the user input is "deadline".
     *
     * @param commandDetails Command inputted by user.
     * @return DeadlineCommand.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    public static DeadlineCommand parseDeadline(String[] commandDetails) throws DukeTaskNotFoundException {
        if (commandDetails.length > 1) {
            String[] stringArray = commandDetails[1].split("/", 2);
            if (stringArray.length > 1 && stringArray[1].split(" ", 2).length > 1) {
                return new DeadlineCommand(stringArray);
            } else {
                throw new DukeTaskNotFoundException(" ERROR... DEADLINE DATE EMPTY. \n PLEASE TRY AGAIN. ");
            }
        } else {
            throw new DukeTaskNotFoundException(" ERROR... DEADLINE DESCRIPTION EMPTY . \n PLEASE TRY AGAIN. ");
        }
    }

    /**
     * Handles the case where the user input is "event".
     *
     * @param commandDetails Command inputted by user.
     * @return EventCommand.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    public static EventCommand parseEvent(String[] commandDetails) throws DukeTaskNotFoundException {
        if (commandDetails.length > 1) {
            String[] stringArray = commandDetails[1].split("/", 2);
            if (stringArray.length > 1 && stringArray[1].split(" ", 2).length > 1) {
                return new EventCommand(stringArray);
            } else {
                throw new DukeTaskNotFoundException(" ERROR... EVENT DATE EMPTY. \n PLEASE TRY AGAIN. ");
            }
        } else {
            throw new DukeTaskNotFoundException(" ERROR... EVENT DESCRIPTION EMPTY. \n PLEASE TRY AGAIN. ");
        }
    }

    /**
     * Handles the case where the user input is "delete".
     *
     * @param commandDetails Command inputted by user.
     * @return DeleteCommand.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    public static DeleteCommand parseDelete(String[] commandDetails) throws DukeTaskNotFoundException {
        if (commandDetails.length > 1 && Character.isDigit(commandDetails[1].charAt(0))) {
            return new DeleteCommand(commandDetails);
        } else {
            throw new DukeTaskNotFoundException(" ERROR... NON-INTEGER RECOGNIZED OR TASK NUMBER NOT INPUTTED. \n " +
                    "PLEASE TRY AGAIN. ");
        }
    }

    /**
     * Handles the case where the user input is "undo".
     *
     * @param commandDetails Command inputted by user.
     * @return UndoCommand.
     * @throws DukeTaskNotFoundException If the task is not found.
     */
    public static UndoCommand parseUndo(String[] commandDetails) throws DukeTaskNotFoundException {
        if (commandDetails.length > 1 && Character.isDigit(commandDetails[1].charAt(0))) {
            return new UndoCommand(commandDetails);
        } else {
            throw new DukeTaskNotFoundException(" ERROR... NON-INTEGER RECOGNIZED OR TASK NUMBER NOT INPUTTED. \n " +
                    "PLEASE TRY AGAIN. ");
        }
    }

    public static FindCommand parseFind(String keyword) {
        return new FindCommand(keyword);
    }

}
