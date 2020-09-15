package fei.tool;

import fei.command.*;
import fei.exception.FeiException;
import fei.task.Deadline;
import fei.task.Event;
import fei.task.Task;
import fei.task.ToDo;

public class Parser {

    /**
     * This method parse a task string into its corresponding Task.
     *
     * @param taskString Saved line from file path.
     * @return Corresponding Task.
     * @throws FeiException when it is none of the known task.
     */
    public static Task parseTask(String taskString) throws FeiException {
        String[] words = taskString.split(" \\| ");
        String type = words[0];
        boolean isDone = Boolean.parseBoolean(words[1]);
        String description = words[2];
        try {
            switch (type) {
            case "T":
                return new ToDo(description, isDone);
            case "E":
                return new Event(description, isDone, words[3]);
            case "D":
                return new Deadline(description, isDone, words[3]);
            default:
                return null;
            }
        } catch (Exception e) {
            throw FeiException.loadingException();
        }
    }

    /**
     * This method deals with making sense of the user command.
     *
     * @param cmd Full string of command entered by the user.
     * @return a Command object.
     * @throws FeiException when Parser fails to parse a command.
     */
    public static Command parse(String cmd) throws FeiException {
        String[] words = cmd.split(" ");
        String command = words[0];
        if (words.length == 1) {
            return parseSingleWord(command);
        }

        String description = cmd.split(" ", 2)[1];
        return parseMultiWord(command, description);

    }

    public static Command parseSingleWord(String cmd) throws FeiException {
        switch (cmd) {
        case "hi":
        case "hello":
            return new GreetingCommand();
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "stats":
            return new StatsCommand();
        case "done":
        case "delete":
            throw FeiException.operationException();
        case "todo":
        case "deadline":
        case "event":
        case "find":
            throw FeiException.emptyDescriptionException();
        default:
            throw FeiException.defaultException();
        }
    }

    public static Command parseMultiWord(String cmd, String description) throws FeiException {
        switch (cmd) {
        case "done":
            return parseDone(description);

        case "delete":
            return parseDelete(description);

        case "todo":
            ToDo todo = new ToDo(description);
            return new AddCommand(todo);

        case "deadline":
            return parseDeadline(description);

        case "event":
            return parseEvent(description);

        case "find":
            return parseFind(description);

        default:
            return new UnknownCommand();
        }
    }

    private static Command parseDone(String index) throws FeiException {
        try {
            int doneIndex = Integer.parseInt(index);
            return new DoneCommand(doneIndex);
        } catch (Exception e) {
            throw FeiException.invalidIndexException();
        }
    }

    private static Command parseDelete(String index) throws FeiException {
        try {
            int deleteIndex = Integer.parseInt(index);
            return new DeleteCommand(deleteIndex);
        } catch (Exception e) {
            throw FeiException.invalidIndexException();
        }
    }

    private static Command parseDeadline(String description) {
        String[] contentAndDate = description.split(" /by ");
        assert contentAndDate.length == 2: FeiException.deadlineException();
        Deadline ddl = new Deadline(contentAndDate[0], contentAndDate[1]);
        return new AddCommand(ddl);
    }

    private static Command parseEvent(String description) {
        String[] contentAndTime = description.split(" /at ");
        assert contentAndTime.length == 2: FeiException.eventException();
        Event e = new Event(contentAndTime[0], contentAndTime[1]);
        return new AddCommand(e);
    }

    private static Command parseFind(String description) {
        assert description.split(" ").length == 1: FeiException.findException();
        return new FindCommand(description);
    }

}
