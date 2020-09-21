import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to handle parsing of input into commands.
 */
public class Parser {

    /**
     * Parses the given command in string form into a Command object.
     *
     * @param textCommand the command to be parsed in string form.
     * @return Command after being parsed.
     * @throws EmptyBodyException    If command in string form is empty.
     * @throws UnknownInputException If command in string form is not a recognised command.
     * @throws  MissingInputException If required parameters are not supplied.
     */
    public static Command parse(String textCommand) throws EmptyBodyException,
            UnknownInputException, MissingInputException {
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

    /**
     * Parses the given notes portion of command in string form into a Command object.
     *
     * @param command the notes portion of command to be parsed in string form.
     * @return Command after being parsed.
     * @throws EmptyBodyException    If command in string form is empty.
     * @throws UnknownInputException If command in string form is not a recognised command.
     * @throws MissingInputException If a parameter is missing.
     */
    public static Command parseNotesCommand(String command)
            throws UnknownInputException, EmptyBodyException, MissingInputException {
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

                if (rawSplitParameters.length > 1) {
                    String name = rawSplitParameters[0];
                    Object value = rawSplitParameters[1];

                    parameters.put(name, value);
                }
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
                    throw new UnknownInputException(entry.getKey(), "a parameter name");
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
                throw new MissingInputException("a parameter", "title, descripton and priority");
            }
        }
        case "list": {
            return new ListNotesCommand();
        }
        case "delete": {
            if (remaining.equals("")) {
                throw new EmptyBodyException("note number", "note");
            }
            int taskNumber = Integer.parseInt(remaining);
            return new DeleteNoteCommand(taskNumber);
        }
        default: {
            throw new UnknownInputException(firstWord, "a notes command");
        }
        }

    }

    /**
     * Parses a string representing priority into a Priority object.
     * @param priority String form of priority.
     * @return Priority object with priority.
     * @throws UnknownInputException If string does not represent a recognised priority.
     */
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
            throw new UnknownInputException(priority, "a priority");
        }
    }
}
