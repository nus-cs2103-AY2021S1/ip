package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import duke.command.AddCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.ListTagCommand;
import duke.command.TagCommand;
import duke.command.UnknownCommand;
import duke.exception.DukeException;

public class Parser {
    /**
     * Parses the input and turn it into a Command.
     *
     * @param input input to be parsed
     * @return the Command that the input belongs to
     * @throws DukeException DukeException
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        Command command;

        switch (inputArr[0]) {
        case "bye":
            return new ExitCommand();
        case "list":
            command = new ListCommand();
            break;
        case "done":
            try {
                command = new DoneCommand(Integer.parseInt(inputArr[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid format!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please include item number!");
            }
            break;
        case "find":
            try {
                command = new FindCommand(input.substring(5));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please include description!");
            }
            break;
        case "tag":
            try {
                command = new TagCommand(input.substring(4));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please include description!");
            }
            break;
        case "tags":
            command = new ListTagCommand();
            break;
        case "delete":
            try {
                command = new DeleteCommand(Integer.parseInt(inputArr[1]));
            } catch (NumberFormatException e) {
                throw new DukeException("Invalid format!");
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new DukeException("Please include item number!");
            }
            break;
        case "todo":
            try {
                command = new AddCommand("todo", input.substring(5));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please include description!");
            }
            break;
        case "deadline":
            try {
                command = new AddCommand("deadline", input.substring(9));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please include description!");
            }
            break;
        case "event":
            try {
                command = new AddCommand("event", input.substring(6));
            } catch (StringIndexOutOfBoundsException e) {
                throw new DukeException("Please include description!");
            }
            break;
        default:
            command = new UnknownCommand();
            break;
        }
        return command;
    }

    /**
     * Parses users' input into LocalDateTime object.
     *
     * @param input users' input
     * @param hasTag boolean true if input contains a tag
     * @return LocalDateTime
     */
    public static LocalDateTime parseTime(String input, boolean hasTag) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HHmm");
        LocalDateTime time;
        if (hasTag) {
            time = LocalDateTime.parse(input.split(" @")[0]
                    .replace(' ', 'T'), formatter);

        } else {
            time = LocalDateTime.parse(input.replace(' ', 'T'), formatter);
        }
        return time;
    }

    /**
     * Parses users' input into a Tag string.
     *
     * @param input users' input
     * @return Tag string
     * @throws DukeException if tag is empty
     */
    public static String parseTag(String input) throws DukeException {
        String tag;
        try {
            tag = input.split(" @")[1];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("Tag cannot be empty!");
        }
        return tag;
    }
}


