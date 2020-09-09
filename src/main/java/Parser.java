import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.MissingFormatArgumentException;

/**
 * Class to handle parsing of input into commands.
 */
public class Parser {

    /**
     * Parses the given command in string form into a logic.commands.Command object.
     *
     * @param textCommand the command to be parsed in string formed.
     * @return logic.commands.Command after being parsed.
     * @throws EmptyBodyException    If command in string form is empty.
     * @throws UnknownInputException If command in string form is not a recognised command.
     */
    public static Command parse(String textCommand) throws EmptyBodyException, UnknownInputException {
        String[] words = textCommand.split(" ", 2);
        String firstWord = words[0];
        String remaining = words.length > 1 ? words[1] : "";

        switch (firstWord) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "delete": {
            if (remaining.equals("")) {
                throw new EmptyBodyException("task number", "task");
            }
            int taskNumber = Integer.parseInt(remaining);
            return new DeleteCommand(taskNumber);
        }
        case "done": {
            if (remaining.equals("")) {
                throw new EmptyBodyException("task number", "task");
            }
            int taskNumber = Integer.parseInt(remaining);
            return new DoneCommand(taskNumber);
        }
        case "todo": {
            return new AddCommand(TaskType.TODO, remaining);
        }
        case "deadline": {
            return new AddCommand(TaskType.DEADLINE, remaining);
        }
        case "event": {
            return new AddCommand(TaskType.EVENT, remaining);
        }
        case "find": {
            return new FindCommand(remaining);
        }
        case "notes": {
            return parseNotesCommand(remaining);
        }
        default:
            throw new UnknownInputException(firstWord);
        }
    }

    // todo need change: right now it's to add notes only but might need to change for editing notes
    public static Command parseNotesCommand(String command) throws UnknownInputException {
        Validator.requireNonNull(command);

        String[] words = command.split(" ", 2);
        String firstWord = words[0];
        String remaining = words.length > 1 ? words[1] : "";

        String title = null;
        String description = null;
        Priority priority = null;

        if (words.length > 1) {

            // Formatting command parameters
            List<String> rawParameters = Arrays.asList(remaining.split(" (?=\\w*[a-z]+\\/)"));
            Map<String, Object> parameters = new HashMap<>();

            rawParameters.forEach(parameter -> {
                String[] rawSplitParameters = parameter.split("/", 2);

                String name = rawSplitParameters[0];
                Object value = rawSplitParameters[1];

                parameters.put(name, value);
            });

            // Parsing command parameters
            for (Map.Entry<String, Object> entry : parameters.entrySet()) {
                switch (entry.getKey()) {
                case "title":
                case "t": {
                    title = (String) entry.getValue();
                    break;
                }
                case "description":
                case "d": {
                    description = (String) entry.getValue();
                    break;
                }
                case "priority":
                case "p": {
                    priority = parsePriority((String) entry.getValue());
                    break;
                }
                default: {
                    throw new UnknownInputException("Unknown parameter name " + entry.getKey()); //todo change to duke exceptions
                }
                }
            }
        }

        switch (firstWord) {
        case "add": {
            try {
                Validator.requireNonNull(title, description, priority);
                return new AddNoteCommand(title, description, priority);
            } catch (NullPointerException e) {
                e.printStackTrace();
                throw new MissingFormatArgumentException(""); // todo: need implement
            }
        }
        case "list": {
            System.out.println("Notes list");
            return new ListNotesCommand();
        }
        default: {
            throw new UnknownInputException("Unknown notes command: " + firstWord); //todo change to duke exceptions
        }
        }

    }

    private static Priority parsePriority(String priority) throws UnknownInputException {
        switch (priority) {
        case "high":
        case "h":
        case "High": {
            return Priority.HIGH;
        }
        case "medium":
        case "m":
        case "Medium": {
            return Priority.MEDIUM;
        }
        case "low":
        case "l":
        case "Low": {
            return Priority.LOW;
        }
        default:
            throw new UnknownInputException("Unknown priority: " + priority); // todo: change exception name
        }
    }
}
