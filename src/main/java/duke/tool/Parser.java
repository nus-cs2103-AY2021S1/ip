package duke.tool;

import duke.Commands;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.AddCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;

import duke.exception.AmbiguousInputException;
import duke.exception.DeletionIndexEmptyException;
import duke.exception.DescriptionEmptyException;
import duke.exception.DoneIndexEmptyException;
import duke.exception.DukeException;
import duke.exception.TimeEmptyException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a parser to interpret the user's command.
 */
public class Parser {
    private static final DateTimeFormatter acceptedFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Returns a command that associate with the specific input.
     *
     * @param input A complete input stream from user.
     * @return A command to be excuted
     * @throws DukeException An exception that happen in Duke system
     */
    public static Command parse(String input) throws DukeException {
        //Detect ambiguous input
        if (input.equals("")) {
            throw new AmbiguousInputException();
        }

        //Detect the command and give it parameter
        String[] s = input.split(" ", 2);

        //TODO:Detect command with unresonable space (eg. "done ")
        Commands command = Commands.commandType(s[0]);

        switch (command) {
        case CLEAR:
            return new ClearCommand();
        case LIST:
            return new ListCommand();
        case DONE:
            if (s.length == 1) {
                throw new DoneIndexEmptyException();
            }

            int index = Integer.parseInt(s[1]) - 1;

            return new DoneCommand(index);
        case FIND:
            if (s.length == 1) {
                throw new DescriptionEmptyException("find");
            }

            return new FindCommand(s[1]);
        case TODO:
            if (s.length == 1) {
                throw new DescriptionEmptyException("todo");
            }

            return new AddCommand(new Todo(s[1]));
        case DEADLINE: {
            if (s.length == 1) {
                throw new DescriptionEmptyException("dealine");
            }

            String[] set = s[1].split(" /by ");

            if (set.length == 1) {
                throw new TimeEmptyException("deadline");
            }

            return new AddCommand(new Deadline(set[0]
                    , LocalDateTime.parse(set[1], acceptedFormatter)));
        }
        case EVENT: {
            if (s.length == 1) {
                throw new DescriptionEmptyException("event");
            }

            String[] set = s[1].split(" /at ");

            if (set.length == 1) {
                throw new TimeEmptyException("event");
            }

            return new AddCommand(new Event(set[0], LocalDateTime.parse(set[1], acceptedFormatter)));
        }
        case DELETE: {
            if (s.length == 1) {
                throw new DeletionIndexEmptyException();
            }

            return new DeleteCommand(Integer.parseInt(s[1]) - 1);
        }
        case EXIT:
            return new ExitCommand();
        default:
            throw new AmbiguousInputException();
        }

    }
}
