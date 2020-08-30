import java.util.Scanner;

public class Parser {

    public static Command parse(String inputLine) throws DukeException {
        Scanner scanner = new Scanner(inputLine);
        String action = scanner.next();
        int index;
        String remainingString;
        String[] parsedStrings;

        switch (action) {
            case "list":
                return new ListCommand();
            case "bye":
                return new ExitCommand();
            case "todo":
            case "deadline":
            case "event":
                remainingString = scanner.nextLine();
                parsedStrings = parseRemainingString(action, remainingString);
                return new AddCommand(parsedStrings);
            case "done":
                index = scanner.nextInt() - 1;
                return new DoneCommand(index);
            case "delete":
                index = scanner.nextInt() - 1;
                return new DeleteCommand(index);
            default:
                throw new DukeException("Sorry, I'm not sure what that means :(");
        }
    }

    public static String[] parseRemainingString(String taskType, String remainingString) throws DukeException {
        if (remainingString.length() == 0) {
            throw new DukeException("The description can't be empty.");
        }

        String description;
        String due;
        String[] splitStrings;

        switch (taskType) {
            case "todo":
                description = remainingString.trim();
                return new String[] {"todo", description};
            case "deadline":
                splitStrings = remainingString.split("/by");
                description = splitStrings[0].trim();
                due = splitStrings[1].trim();
                if (description.length() == 0 || due.length() == 0) {
                    throw new DukeException("Description or due date is empty.");
                }
                return new String[] {"deadline", description, due};
            case "event":
                splitStrings = remainingString.split("/at");
                description = splitStrings[0].trim();
                due = splitStrings[1].trim();
                if (description.length() == 0 || due.length() == 0) {
                    throw new DukeException("Description or due date is empty.");
                }
                return new String[] {"event", description, due};
            default:
                throw new DukeException("I don't know what type of task this is :(");

        }
    }

}
