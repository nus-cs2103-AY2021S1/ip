package main.java.duke;


import main.java.duke.command.ListCommand;
import main.java.duke.command.Command;
import main.java.duke.command.DeleteCommand;
import main.java.duke.command.OwoCommand;
import main.java.duke.command.UwuCommand;
import main.java.duke.command.DoneCommand;
import main.java.duke.command.AddCommand;
import main.java.duke.command.ExitCommand;
import main.java.duke.command.FindCommand;

import main.java.duke.tasks.Deadline;
import main.java.duke.tasks.Todo;

/**
 * Deals with making sense of the user command.
 */
public class Parser {

    /**
     * Returns the command to be executed corresponding to the first word in the string of user input.
     *
     * @param userInput A string of text user typed into command line.
     * @return Command to be executed.
     * @throws DukeException If Duke does not understand user command.
     */
    public static Command parse(String userInput) throws DukeException {

        String[] splitCommand = userInput.split(" ", 2);
        String commandWord = splitCommand[0];
        switch (commandWord) {

        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case FindCommand.COMMAND_WORD_FIND:
            if (checkInputLength(splitCommand, 2)) {
                return new FindCommand(splitCommand[1]);
            }
        case DeleteCommand.COMMAND_WORD:
            if (checkInputLength(splitCommand, 2)) {
                return prepareDelete(splitCommand[1]);
            }
        case DoneCommand.COMMAND_WORD:
            if (checkInputLength(splitCommand, 2)) {
                return prepareDone(splitCommand[1]);
            }

        case OwoCommand.COMMAND_WORD:
            return new OwoCommand();

        case UwuCommand.COMMAND_WORD:
            return new UwuCommand();

        case AddCommand.COMMAND_WORD_TODO:
            // Fallthrough
        case AddCommand.COMMAND_WORD_DEADLINE:
            // Fallthrough
        case AddCommand.COMMAND_WORD_EVENT:
            if (checkInputLength(splitCommand, 2)) {
                return prepareAdd(commandWord, splitCommand[1]);
            }
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        default:
            throw new DukeException("I don't understand what you're saying HMM...");
        }

    }

    private static boolean checkInputLength(String[] input, int len) throws DukeException {
        if (input.length < len) {
            throw new DukeException("Input is missing some arguments!");
        } else {
            return true;
        }
    }

    private static Command prepareDelete(String input) throws DukeException {
        try {
            int taskNum = Integer.parseInt(input);
            return new DeleteCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    private static Command prepareDone(String input) throws DukeException{
        try {
            int taskNum = Integer.parseInt(input);
            return new DoneCommand(taskNum);
        } catch (NumberFormatException e) {
            throw new DukeException("Invalid task number!");
        }
    }

    private static Command prepareAdd(String command, String descript) throws DukeException {
        switch (command) {
        case ("todo"):
            return new AddCommand(new Todo(descript));
        case ("deadline"):
            String[] delete = descript.split(" /by ", 2);
            if (checkInputLength(delete, 2)) {
                return new AddCommand(new Deadline(delete[0], delete[1].replace('/', '-')));
            }
        case ("event"):
            String[] event = descript.split(" /at ", 2);
            if (checkInputLength(event, 2)) {
                return new AddCommand(new Deadline(event[0], event[1].replace('/', '-')));
            }
        default:
            throw new DukeException("Couldn't add item..");
        }
    }
}
