package raythx.grandma.parser;

import raythx.grandma.command.AddDeadlineCommand;
import raythx.grandma.command.AddEventCommand;
import raythx.grandma.command.AddTodoCommand;
import raythx.grandma.command.Command;
import raythx.grandma.command.DeleteCommand;
import raythx.grandma.command.DoneCommand;
import raythx.grandma.command.ExitCommand;
import raythx.grandma.command.FindCommand;
import raythx.grandma.command.HelpCommand;
import raythx.grandma.command.ListCommand;
import raythx.grandma.exception.AdditionalArgumentException;
import raythx.grandma.exception.DukeException;
import raythx.grandma.exception.EmptyDescriptionException;
import raythx.grandma.exception.IllegalInputException;
import raythx.grandma.exception.InsufficientArgumentException;
import raythx.grandma.exception.InvalidIndexException;
import raythx.grandma.exception.UnknownCommandException;
import raythx.grandma.exception.WrongArgumentException;
import raythx.grandma.task.TaskList;

/**
 * Deals with making sense of user inputs.
 */
public class Parser {

    /**
     * Parses the user data by making processing input.
     *
     * @param tasks List of tasks at the moment.
     * @param input String to be parsed.
     * @return
     */
    public Command parse(TaskList tasks, String input) throws DukeException, NumberFormatException {
        String[] splitBySpace = input.split(" ", 2);
        if (input.isEmpty()) {
            throw new EmptyDescriptionException();
        }
        if (input.contains("|")) {
            throw new IllegalInputException();
        }
        switch (splitBySpace[0]) {
        case "bye":
            if (splitBySpace.length > 1) {
                throw new AdditionalArgumentException();
            }
            return new ExitCommand();
        case "list":
            if (splitBySpace.length > 1) {
                throw new AdditionalArgumentException();
            }
            return new ListCommand();
        case "help":
            if (splitBySpace.length > 1) {
                throw new AdditionalArgumentException();
            }
            return new HelpCommand();
        case "done":
            if (splitBySpace.length < 2) {
                throw new InsufficientArgumentException();
            }
            int doneIndex;
            try {
                doneIndex = Integer.parseInt(splitBySpace[1]) - 1;
            } catch (NumberFormatException exception) {
                throw new WrongArgumentException();
            }
            if (doneIndex < 0 || doneIndex > tasks.getSize() - 1) {
                throw new InvalidIndexException();
            }
            return new DoneCommand(doneIndex);
        case "delete":
            if (splitBySpace.length < 2) {
                throw new InsufficientArgumentException();
            }
            int deleteIndex;
            try {
                deleteIndex = Integer.parseInt(splitBySpace[1]) - 1;
            } catch (NumberFormatException exception) {
                throw new WrongArgumentException();
            }
            if (deleteIndex < 0 || deleteIndex > tasks.getSize() - 1) {
                throw new InvalidIndexException();
            }
            return new DeleteCommand(deleteIndex);
        case "find":
            if (splitBySpace.length < 2) {
                throw new EmptyDescriptionException();
            }
            return new FindCommand(splitBySpace[1]);
        case "todo":
            if (splitBySpace.length < 2) {
                throw new EmptyDescriptionException();
            } else {
                return new AddTodoCommand(splitBySpace[1]);
            }
        case "deadline":
            if (splitBySpace.length < 2) {
                throw new EmptyDescriptionException();
            } else {
                return new AddDeadlineCommand(splitBySpace[1]);
            }
        case "event":
            if (splitBySpace.length < 2) {
                throw new EmptyDescriptionException();
            } else {
                return new AddEventCommand(splitBySpace[1]);
            }
        default:
            throw new UnknownCommandException();
        }
    }
}
