public class Parser {

    public static Command parse(String userInput) throws EmptyTaskDescriptionException, DukeDateTimeParseException {
        String[] arr = userInput.trim().split(" ", 2);
        switch (arr[0]) {
            case "bye":
                return parseBye();
            case "list":
                return parseList();
            case "done":
                return parseDone(arr[1]);
            case "todo":
            case "deadline":
            case "event":
                if (arr.length < 2) {
                    throw new EmptyTaskDescriptionException(arr[0]);
                }
                return parseAdd(arr[0], arr[1]);
            case "delete":
                return parseDelete(arr[1]);
            case "today":
                return parseToday();
            default:
                throw new InvalidCommandException();
        }
    }

    private static Command parseToday() {
        return new TodayCommand();
    }

    private static AddCommand parseAdd(String commandName, String arguments) throws EmptyTaskDescriptionException {
        Task task = null;
        if (arguments.equals("")) {
            throw new EmptyTaskDescriptionException(commandName);
        }
        switch (commandName) {
            case "todo":
                task = new Todo(arguments);
                break;
            case "deadline": {
                String[] parsed = arguments.split(" /by ");
                if (parsed.length < 2) {
                    throw new EmptyDueDateException();
                }
                if (parsed[0].strip().equals("")) {
                    throw new EmptyTaskDescriptionException(commandName);
                }
                task = new Deadline(parsed[0], DukeDateTimeParser.parse(parsed[1]));
                break;
            }
            case "event": {
                String[] parsed = arguments.split(" /at ");
                if (parsed.length < 2) {
                    throw new EmptyEventDateException();
                }
                if (parsed[0].strip().equals("")) {
                    throw new EmptyTaskDescriptionException(commandName);
                }
                task = new Event(parsed[0], DukeDateTimeParser.parse(parsed[1]));
                break;
            }
            default:
                // todo: throw exception
        }
        return new AddCommand(task);
    }


    private static DoneCommand parseDone(String taskDone) throws InvalidTaskException {
        try {
            int index = Integer.parseInt(taskDone) - 1;
            return new DoneCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException();
        }
    }

    private static DeleteCommand parseDelete(String taskDeleted) throws InvalidTaskException {
        try {
            int index = Integer.parseInt(taskDeleted) - 1;
            return new DeleteCommand(index);
        } catch (NumberFormatException e) {
            throw new InvalidTaskException();
        }
    }

    private static ListCommand parseList() {
        return new ListCommand();
    }

    private static ByeCommand parseBye() {
        return new ByeCommand();
    }
}
