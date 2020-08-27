package duke;

import duke.command.*;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidCommandException;
import duke.exception.InvalidFormatException;

import java.util.Arrays;

public class Parser {

    public static Command parse(String input) throws InvalidCommandException {
        String[] splitted = input.split("\\s+");
        String command = splitted[0];
        if (command.equals("help")) {
            return new HelpCommand();
        } else if (command.equals("bye")) {
            return new QuitCommand();
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("done")) {
            return new DoneCommand();
        } else if (command.equals("delete")) {
            return new DeleteCommand();
        } else if (command.equals("todo")) {
            return new TodoCommand();
        } else if (command.equals("deadline")) {
            return  new DeadlineCommand();
        } else if (command.equals("event")) {
            return new EventCommand();
        } else {
            throw new InvalidCommandException();
        }
    }

    public static int parseTaskId(String input) {
        String rawNum = input.replaceAll("[^0-9]", "");
        int taskId = Integer.parseInt(rawNum) - 1;
        return taskId;
    }

    public static String parseTodo(String input) throws EmptyDescriptionException {
        String[] splitted = input.split("\\s+");
        String[] title = Arrays.copyOfRange(splitted, 1, splitted.length);
        if (title.length == 0) throw new EmptyDescriptionException("ToDo");
        return String.join(" ", title);
    }

    public static String[] parseDeadline(String input) throws EmptyDescriptionException, InvalidFormatException {
        String[] splitted = input.split("\\s+");
        if (splitted.length == 1) throw new EmptyDescriptionException("Deadline");

        int separator = getIndex(splitted, "/by");

        if (separator == -1) throw new InvalidFormatException("/by parameter");

        String[] titles = Arrays.copyOfRange(splitted, 1, separator);
        String[] deadlines = Arrays.copyOfRange(splitted, separator + 1, splitted.length);

        if (deadlines.length == 0) throw new EmptyDescriptionException("/by parameter");

        String title = String.join(" ", titles);
        String deadline = String.join(" ", deadlines);
        String[] pair = {title, deadline};

        return pair;
    }

    private static int getIndex(String[] words, String target) {
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(target)) return i;
        }
        return -1;
    }

    public static String[] parseEvent(String input) throws EmptyDescriptionException, InvalidFormatException {
        String[] splitted = input.split("\\s+");
        if (splitted.length == 1) throw new EmptyDescriptionException("Event");

        int separator = getIndex(splitted, "/at");

        if (separator == -1) throw new InvalidFormatException("/at parameter");

        String[] titles = Arrays.copyOfRange(splitted, 1, separator);
        String[] deadlines = Arrays.copyOfRange(splitted, separator + 1, splitted.length);

        if (deadlines.length == 0) throw new EmptyDescriptionException("/at parameter");

        String title = String.join(" ", titles);
        String deadline = String.join(" ", deadlines);
        String[] pair = {title, deadline};

        return pair;
    }
}
