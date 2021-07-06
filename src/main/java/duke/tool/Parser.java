package duke.tool;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.ValidCommand;
import duke.command.AddCommand;
import duke.command.ClearCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.RescheduleCommand;
import duke.exception.AmbiguousInputException;
import duke.exception.DateFormatException;
import duke.exception.DeletionIndexEmptyException;
import duke.exception.DescriptionEmptyException;
import duke.exception.DoneIndexEmptyException;
import duke.exception.DukeException;
import duke.exception.TimeEmptyException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;


/**
 * Represents a parser to interpret the user's command.
 */
public class Parser {

    /**
     * Formatter for input date
     */
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

        ValidCommand command = ValidCommand.commandType(s[0]);

        try {
            switch (command) {
            case RESCHEDULE:
                String[] indexAndDate = s[1].split(" /to ");

                if (indexAndDate.length != 2) {
                    throw new DukeException("Parameter error.");
                }

                return new RescheduleCommand(Integer.parseInt(indexAndDate[0]),
                        LocalDateTime.parse(indexAndDate[1], acceptedFormatter));
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

                assert set.length > 1;
                return new AddCommand(new Deadline(set[0], LocalDateTime.parse(set[1], acceptedFormatter)));
            }
            case EVENT: {
                if (s.length == 1) {
                    throw new DescriptionEmptyException("event");
                }

                String[] set = s[1].split(" /at ");

                if (set.length == 1) {
                    throw new TimeEmptyException("event");
                }

                assert set.length > 1;
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
        } catch (DateTimeException e) {
            throw new DateFormatException();
        }

    }

}
