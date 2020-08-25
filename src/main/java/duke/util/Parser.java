package duke.util;

import duke.command.ByeCommand;
import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.FindCommand;
import duke.command.HelpCommand;
import duke.command.ListCommand;
import duke.command.ToDoCommand;
import duke.exception.DukeException;
import duke.exception.InvalidDeadlineException;
import duke.exception.InvalidEventException;
import duke.exception.InvalidToDoException;

import java.util.Arrays;
import java.util.Date;

/**
 * Parses an input into a command.
 */
public class Parser {
    /**
     * Parses a String input into a command.
     * 
     * @param input The input to parse.
     * @return Command associated with the input.
     * @throws DukeException If the input does not match any command.
     */
    public static Command parse(String input) throws DukeException {
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("help")) {
            return new HelpCommand();
        } else if (input.startsWith("deadline")) {
            try {
                String[] inputArray = input.substring(input.indexOf(' ') + 1).split(" /by ");
                String description = inputArray[0];
                Date by = DateFormatter.extractTimestampInput(inputArray[1]);
                return new DeadlineCommand(description, by);
            } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                throw new InvalidDeadlineException();
            }
        } else if (input.matches("delete \\d+")) {
            int index = Integer.parseInt(input.split(" ")[1]);
            return new DeleteCommand(index);
        } else if (input.matches("done \\d+")) {
            int index = Integer.parseInt(input.split(" ")[1]);
            return new DoneCommand(index);
        } else if (input.startsWith("event")) {
            try {
                String[] inputArray = input.substring(input.indexOf(' ') + 1).split(" /at ");
                String description = inputArray[0];
                Date at = DateFormatter.extractTimestampInput(inputArray[1]);
                return new EventCommand(description, at);
            } catch (ArrayIndexOutOfBoundsException | DukeException e) {
                throw new InvalidEventException();
            }
        } else if (input.equals("list")) {
            return new ListCommand();
        } else if (input.startsWith("todo")) {
            String[] split = input.split(" ");
            if (split.length == 1) {
                throw new InvalidToDoException();
            }
            String[] descriptionArray = Arrays.copyOfRange(split, 1, split.length);
            String description = String.join(" ", descriptionArray);
            return new ToDoCommand(description);
        } else if (input.startsWith("find")) {
            String[] split = input.split("find ");
            String toFind = split[1];
            return new FindCommand(toFind);
        } else {
            throw new DukeException();
        }
    }
}
