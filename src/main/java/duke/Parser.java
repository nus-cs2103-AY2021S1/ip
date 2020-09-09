package duke;

import java.time.LocalDate;
import java.util.Arrays;

/**
 * Parse an input given to the Duke to a command.
 */
public class Parser {
    private enum CommandType {
        find, list, done, delete, todo, deadline, event, update, bye
    }

    private static String parseDescription(String[] data, String timeDivider) throws DukeException {

        if (data.length < 1 || data[1].equals(timeDivider)) {
            throw new DukeException("The description can't be blank :(.");
        }

        StringBuilder description = new StringBuilder(data[1]);
        int idx = 2;
        while (idx < data.length && !data[idx].equals(timeDivider)) {
            description.append(" ").append(data[idx]);
            idx++;
        }

        return description.toString();
    }

    private static LocalDate parseTime(String[] data, String timeDivider) throws DukeException {
        int idx = 1;
        while (idx < data.length && !data[idx].equals(timeDivider)) {
            idx++;
        }
        idx++;

        if (idx >= data.length) {
            throw new DukeException(
                    "Please specify the date in this format: \"" + timeDivider + " <date>\". ");
        }

        StringBuilder time = new StringBuilder(data[idx]);
        idx++;
        while (idx < data.length) {
            time.append(" ").append(data[idx]);
            idx++;
        }

        LocalDate date;

        try {
            date = LocalDate.parse(time.toString());
        } catch (Exception e) {
            throw new DukeException("Please enter the date in yyyy-mm-dd format.");
        }

        return date;
    }

    /**
     * Parse the input string to a command.
     *
     * @param line the string to be parsed.
     * @return command from the parsed string.
     * @throws DukeException input string is an invalid command.
     */
    public static Command parse(String line) throws DukeException {
        String[] commandLine = line.split(" ");
        int idx;

        CommandType commandEnum;
        Command command;

        try {
            commandEnum = CommandType.valueOf(commandLine[0]);
        } catch (Exception e) {
            throw new DukeException("I'm sorry but I don't recognize your commandLine T__T.");
        }

        switch (commandEnum) {
        case find:
            command = new FindCommand(Parser.parseDescription(commandLine, ""));
            break;
        case list:
            command = new ListCommand();
            break;
        case done:
            idx = Integer.parseInt(commandLine[1]);
            command = new DoneCommand(idx);
            break;
        case delete:
            idx = Integer.parseInt(commandLine[1]);
            return new DeleteCommand(idx);
        case todo:
            command = new AddCommand(new Todo(Parser.parseDescription(commandLine, "")));
            break;
        case deadline:
            command = new AddCommand(new Deadline(Parser.parseDescription(commandLine, "/by"),
                    Parser.parseTime(commandLine, "/by")));
            break;
        case event:
            command = new AddCommand(
                    new Event(Parser.parseDescription(commandLine, "/at"), Parser.parseTime(commandLine, "/at")));
            break;
        case update:
            if (commandLine.length < 3) {
                throw new DukeException("Update with this format: \"update date/description <updatedValue>\"");
            }

            idx = Integer.parseInt(commandLine[2]);

            if (commandLine[1].equals("date")) {
                command = new UpdateTimeCommand(idx, Parser.parseTime(
                        Arrays.copyOfRange(commandLine, 1, commandLine.length), commandLine[2]));
            } else if (commandLine[1].equals("description")) {
                command = new UpdateDescriptionCommand(idx, Parser.parseDescription(
                        Arrays.copyOfRange(commandLine, 2, commandLine.length), ""));
            } else {
                throw new DukeException("Can only update date or description");
            }
            break;
        case bye:
            command = new ByeCommand();
            break;
        default:
            throw new DukeException("I'm sorry but I don't recognize your command T__T.");
        }
        assert command != null;
        return command;
    }
}
