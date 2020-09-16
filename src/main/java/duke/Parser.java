package duke;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.OwoCommand;
import duke.command.StatsCommand;
import duke.command.UwuCommand;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Todo;

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
        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();
        case ListCommand.COMMAND_WORD:
            return new ListCommand();
        case OwoCommand.COMMAND_WORD:
            return new OwoCommand();
        case UwuCommand.COMMAND_WORD:
            return new UwuCommand();
        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();
        case StatsCommand.COMMAND_WORD:
            return new StatsCommand();
        case FindCommand.COMMAND_WORD_FIND:
            return prepareFind(splitCommand);
        case DeleteCommand.COMMAND_WORD:
            return prepareDelete(splitCommand);
        case DoneCommand.COMMAND_WORD:
            return prepareDone(splitCommand);
        case AddCommand.COMMAND_WORD_TODO:
            // Fallthrough
        case AddCommand.COMMAND_WORD_DEADLINE:
            // Fallthrough
        case AddCommand.COMMAND_WORD_EVENT:
            return prepareAdd(splitCommand);
        default:
            throw new DukeException("I don't understand what you're saying HMM...");
        }
    }

    private static Command prepareFind(String[] input) throws DukeException {
        if (input.length >= 2) {
            return new FindCommand(input[1]);
        } else {
            throw new DukeException("Input is missing some arguments!");
        }
    }

    private static Command prepareDelete(String[] input) throws DukeException {
        if (input.length >= 2) {
            try {
                int taskNum = Integer.parseInt(input[1]);
                return new DeleteCommand(taskNum);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number!");
            }
        } else {
            throw new DukeException("Input is missing some arguments!");
        }
    }

    private static Command prepareDone(String[] input) throws DukeException {
        if (input.length >= 2) {
            try {
                int taskNum = Integer.parseInt(input[1]);
                return new DoneCommand(taskNum);
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid task number!");
            }
        } else {
            throw new DukeException("Input is missing some arguments!");
        }
    }

    private static Command prepareAdd(String[] input) throws DukeException {
        if (input.length >= 2) {
            switch (input[0]) {
            case ("todo"):
                return new AddCommand(new Todo(input[1]));
            case ("deadline"):
                return prepareDeadline(input[1]);
            case ("event"):
                return prepareEvent(input[1]);
            default:
                throw new DukeException("Couldn't add item..");
            }
        } else {
            throw new DukeException("Input is missing some arguments!");
        }
    }

    private static Command prepareDeadline(String input) throws DukeException {
        String[] deadline = input.split(" /by ", 2);
        if (deadline.length >= 2) {
            return new AddCommand(new Deadline(deadline[0], deadline[1].replace('/', '-')));
        } else {
            throw new DukeException("Input is missing some arguments!");
        }
    }

    private static Command prepareEvent(String input) throws DukeException {
        String[] event = input.split(" /at ", 2);
        if (event.length >= 2) {
            return new AddCommand(new Event(event[0], event[1].replace('/', '-')));
        } else {
            throw new DukeException("Input is missing some arguments!");
        }
    }
}
