package duke;

import java.util.Scanner;

public class Parser {
    public static Command parse(String line) throws DukeException {
        Scanner sc = new Scanner(line);
        String sentence = sc.next();

        switch (sentence) {
            case "todo":
                return new AddCommand(parseToDo(sc.nextLine()));
            case "deadline":
                return new AddCommand(parseDeadline(sc.nextLine()));
            case "event":
                return new AddCommand(parseEvent(sc.nextLine()));
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "done":
                return new DoneCommand(sc.nextInt());
            case "delete":
                return new DeleteCommand(sc.nextInt());
            default:
                throw new DukeException("Sorry, I'm not sure what that means :(");
        }
    }

    private static String[] parseToDo(String nextLine) throws DukeException {
        if (nextLine.length() <= 0) {
            throw new DukeException("Please give description!");
        }
        String[] words = {"todo", nextLine.trim()};
        return words;
    }

    private static String[] parseDeadline(String nextLine) throws DukeException {
        String[] temp = nextLine.split("/by");
        String description = temp[0].trim();
        String by = temp[1].trim();

        if (description.length() <= 0 || by.length() <= 0) {
            throw new DukeException("Please provide both a description and date in format 'description /by date'!");
        }

        String[] words = {"deadline", description, by};
        return words;
    }

    private static String[] parseEvent(String nextLine) throws DukeException {
        String[] temp = nextLine.split("/at");
        String description = temp[0].trim();
        String at = temp[1].trim();

        if (description.length() <= 0 || at.length() <= 0) {
            throw new DukeException("Please provide both a description and date in format 'description /at date'!");
        }

        String[] words = {"event", description, at};
        return words;
    }
}
