package raythx98.duke.parser;

import raythx98.duke.command.AddCommand;
import raythx98.duke.command.AddDeadlineCommand;
import raythx98.duke.command.AddEventCommand;
import raythx98.duke.command.AddTodoCommand;
import raythx98.duke.command.Command;
import raythx98.duke.command.DeleteCommand;
import raythx98.duke.command.DoneCommand;
import raythx98.duke.command.ExitCommand;
import raythx98.duke.command.FindCommand;
import raythx98.duke.command.InvalidCommand;
import raythx98.duke.command.ListCommand;
import raythx98.duke.exception.DukeException;
import raythx98.duke.exception.EmptyDescriptionException;
import raythx98.duke.exception.InvalidIndexException;
import raythx98.duke.exception.UnknownCommandException;
import raythx98.duke.task.Task;
import raythx98.duke.task.TaskList;

/**
 * Deals with making sense of user inputs.
 */
public class Parser {

    /**
     * Parses the user data by making processing input.
     *
     * @param tasks    List of tasks at the moment.
     * @param nextLine String to be parsed.
     * @return
     */
    public Command parse(TaskList tasks, String nextLine) throws DukeException, NumberFormatException {
        String[] spaceSplit = nextLine.split(" ", 2);
        if (nextLine.isEmpty()) {
            throw new EmptyDescriptionException("Type something leh");
        }
        switch (spaceSplit[0]) {
        case "bye":
            if (spaceSplit.length > 1) {
                throw new DukeException("bye error");
            }
            return new ExitCommand();
        case "list":
            if (spaceSplit.length > 1) {
                throw new DukeException("list error");
            }
            return new ListCommand();
        case "done":
            if (spaceSplit.length < 2) {
                throw new DukeException("done error");
            }
            int doneIndex;
            try {
                doneIndex = Integer.parseInt(spaceSplit[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Not number");
            }
            if (doneIndex < 0 || doneIndex > tasks.getSize() - 1) {
                throw new InvalidIndexException("Simi number lai de");
            }
            return new DoneCommand(doneIndex);
        case "delete":
            if (spaceSplit.length < 2) {
                throw new DukeException("lol");
            }
            int deleteIndex;
            try {
                deleteIndex = Integer.parseInt(spaceSplit[1]) - 1;
            } catch (NumberFormatException e) {
                throw new DukeException("Not number");
            }
            if (deleteIndex < 0 || deleteIndex > tasks.getSize() - 1) {
                throw new InvalidIndexException("Simi number lai de");
            }
            return new DeleteCommand(deleteIndex);
        case "find":
            if (spaceSplit.length < 2) {
                throw new EmptyDescriptionException("Specify key word la oi");
            }
            return new FindCommand(spaceSplit[1]);
        case "todo":
            if (spaceSplit.length < 2) {
                throw new EmptyDescriptionException("Description empty la oi");
            } else {
                return new AddTodoCommand(spaceSplit[1]);
            }
        case "deadline":
            if (spaceSplit.length < 2) {
                throw new EmptyDescriptionException("Description empty la oi");
            } else {
                return new AddDeadlineCommand(spaceSplit[1]);
            }
        case "event":
            if (spaceSplit.length < 2) {
                throw new EmptyDescriptionException("Description empty la oi");
            } else {
                return new AddEventCommand(spaceSplit[1]);
            }
        default:
            throw new UnknownCommandException("Don't understand la oi");
        }
    }
}
