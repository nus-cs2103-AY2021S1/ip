/**
 * Represents a parser that helps to make sense of the user commands.
 */
public class Parser {
    /**
     * Reads the user input and returns the corresponding command according to the command
     * given by the user.
     *
     * @param input The user input into the program.
     * @return The corresponding command for the user input.
     * @throws DukeException If an invalid user input was given.
     */
    public static Command parse(String input) throws DukeException {
        input = input.toLowerCase();
        String[] inputWords = input.split(" ");

        if (inputWords.length == 0) {
            throw new InvalidCommandException();
        }

        String commandWord = inputWords[0];
        boolean isOneWord = inputWords.length == 1;

        if (commandWord.equals("bye") && isOneWord) {
            return new ExitCommand();
        } else if (commandWord.equals("help") && isOneWord) {
            return new HelpCommand();
        } else if (commandWord.equals("list") && isOneWord) {
            return new ListCommand();
        } else if (commandWord.equals("find")) {
            return parseFind(input, isOneWord);
        } else if (commandWord.equals("done")) {
            return parseDone(input, inputWords);
        } else if (commandWord.equals("delete")) {
            return parseDelete(input, inputWords);
        } else if (commandWord.equals("update")) {
            return parseUpdate(input, inputWords);
        } else if (commandWord.equals("todo")) {
            return parseTodo(input, isOneWord);
        } else if (commandWord.equals("deadline")) {
            return parseDeadline(input, isOneWord);
        } else if (commandWord.equals("event")) {
            return parseEvent(input, isOneWord);
        } else {
            throw new InvalidCommandException();
        }
    }

    public static Command parseFind(String input, boolean isOneWord) throws DukeException {
        if (isOneWord) {
            throw new InvalidFindException();
        }

        String keyword = input.substring(4);
        return new FindCommand(keyword);
    }

    public static Command parseDone(String input, String[] inputWords) throws DukeException {
        if (inputWords.length != 2) {
            throw new InvalidDoneException();
        }

        String taskNum = input.substring(5);
        int i = Integer.parseInt(taskNum);
        return new DoneCommand(i);
    }

    public static Command parseDelete(String input, String[] inputWords) throws DukeException {
        if (inputWords.length != 2) {
            throw new InvalidDeleteException();
        }

        String taskNum = input.substring(7);
        int i = Integer.parseInt(taskNum);
        return new DeleteCommand(i);
    }

    public static Command parseUpdate(String input, String[] inputWords) throws DukeException {
        if (inputWords.length < 4) {
            throw new InvalidUpdateException();
        }

        boolean isUpdateName = inputWords[2].equals("/name");
        boolean isUpdateDateTime = inputWords[2].equals("/date");

        if (!isUpdateName && !isUpdateDateTime) {
            throw new InvalidUpdateException();
        }

        String taskNum = inputWords[1];
        int i = Integer.parseInt(taskNum);

        if (isUpdateName) {
            String newTaskName = input.split(" /name ")[1];
            return new UpdateCommand(i, newTaskName, null);
        } else {
            String newTaskDate = input.split(" /date ")[1];
            return new UpdateCommand(i, null, newTaskDate);
        }

    }

    public static Command parseTodo(String input, boolean isOneWord) throws DukeException {
        if (isOneWord) {
            throw new InvalidTodoFormatException();
        }

        String taskDesc = input.substring(5);
        String[] taskInfos = {taskDesc};
        return new AddCommand(TaskType.TODO, taskInfos);
    }

    public static Command parseDeadline(String input, boolean isOneWord) throws DukeException {
        if (isOneWord) {
            throw new InvalidDeadlineFormatException();
        }

        String taskDesc = input.substring(9);
        String[] taskInfos = taskDesc.split(" /by ");

        if (taskInfos.length == 1) {
            throw new InvalidDeadlineFormatException();
        }

        return new AddCommand(TaskType.DEADLINE, taskInfos);
    }

    public static Command parseEvent(String input, boolean isOneWord) throws DukeException {
        if (isOneWord) {
            throw new InvalidEventFormatException();
        }

        String taskDesc = input.substring(6);
        String[] taskInfos = taskDesc.split(" /at ");

        if (taskInfos.length == 1) {
            throw new InvalidEventFormatException();
        }

        return new AddCommand(TaskType.EVENT, taskInfos);
    }
}
