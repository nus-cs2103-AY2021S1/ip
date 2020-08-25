import java.util.Arrays;

public class Parser {

    private static String[] format(String raw) {
        return raw.split(" ");
    }

    public static String getTaskDescription(String[] raw) {
        String[] temp = Arrays.copyOfRange(raw, 1, raw.length);
        return String.join(" ", temp);
    }

    public static Command parse(String input) throws DukeException {
        String[] parsed = Parser.format(input);
        String command = parsed[0];

        switch (command.toLowerCase()) {
        case "bye":
        case "quit":
        case "exit":
            return new ExitCommand();
        case "todo":
        case "event":
        case "deadline":
            String description = Parser.getTaskDescription(parsed);
            return new AddCommand(command, description);
        case "done":
            try {
                int i = Integer.parseInt(parsed[1]);
                return new DoneCommand(i);
            } catch (NumberFormatException nfe) {
                throw new DukeException("Invalid task number!");
            }
        case "list":
            return new ListCommand();
        case "delete":
        case "remove":
            try {
                int i = Integer.parseInt(parsed[1]);
                return new RemoveCommand(i);
            } catch (NumberFormatException nfe) {
                throw new DukeException("Invalid task number!");
            }
        default:
            return new InvalidCommand();
        }
    }
}
