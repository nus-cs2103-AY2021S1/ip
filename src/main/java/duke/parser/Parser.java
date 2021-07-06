package duke.parser;

import duke.DukeException;
import duke.ExceptionTypeEnum;
import duke.command.*;
import duke.task.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Parser {
    static Map<String, String> commandMap = new HashMap<>();

    /**
     * Parses a line of user input, splits it via regex (~), creates a Command object with required information
     * and returns it.
     *
     * @param fullCommand
     * @return the Command represented by the input string
     * @throws DukeException with appropriate message if command encountered is incorrect
     */
    public static Command parse(String fullCommand) throws DukeException {
        String arr[] = fullCommand.split(" ", 2);
        String command = arr[0];
        String remainingText = arr.length == 1 ? null : arr[1].trim();
        String[] taskItems;
        String description;
        Task task;
        int index;

        switch (Parser.getMappedCommand(command)) {
        case "list":
            return parseListCommand(remainingText);

        case "find":
            return parseFindCommand(remainingText);

        case "view":
            return parseScheduleCommand(remainingText);

        case "todo":
            return parseTodo(remainingText);

        case "deadline":
            return parseDeadline(remainingText);

        case "event":
            return parseEvent(remainingText);

        case "note":
            return parseNote(remainingText);

        case "done":
            return parseDoneCommand(remainingText);

        case "delete":
            return parseDeleteCommand(remainingText);

        case "bye":
            return parseByeCommand(remainingText);

        case "unknown":
        default:
            throw new DukeException(ExceptionTypeEnum.UNKNOWN_COMMAND);
        }
    }

    private static ListCommand parseListCommand(String remainingText) throws DukeException {
        if (remainingText != null) {
            throw new DukeException(ExceptionTypeEnum.INCORRECT_LIST);
        }
        return new ListCommand();
    }

    private static FindCommand parseFindCommand(String remainingText) throws DukeException {
        if (remainingText == null) {
            throw new DukeException(ExceptionTypeEnum.MISSING_FIND_KEYWORD);
        }
        return new FindCommand(remainingText);
    }

    private static ScheduleCommand parseScheduleCommand(String remainingText) throws DukeException {
        if (remainingText == null) {
            throw new DukeException(ExceptionTypeEnum.MISSING_SCHEDULE_DATE);
        }
        LocalDate date;
        try {
            date = LocalDate.parse(remainingText);
        } catch (DateTimeParseException e) {
            throw new DukeException(ExceptionTypeEnum.INCORRECT_SCHEDULE_DATE);
        }
        return new ScheduleCommand(date);
    }

    private static AddCommand parseTodo(String remainingText) throws DukeException {
        if (remainingText == null) {
            throw new DukeException(ExceptionTypeEnum.MISSING_TODO_DESCRIPTION);
        }
        Task task = new TodoTask(remainingText);
        return new AddCommand(task);
    }

    private static AddCommand parseEvent(String remainingText) throws DukeException {
        if (remainingText == null) {
            throw new DukeException(ExceptionTypeEnum.MISSING_EVENT_DESCRIPTION);
        }
        String[] taskItems = remainingText.split(" /at ");
        String description = taskItems[0].trim();
        if (taskItems.length == 1) {
            throw new DukeException(ExceptionTypeEnum.MISSING_EVENT_DATE);
        }
        LocalDate at;

        try {
            at = LocalDate.parse(taskItems[1].trim());
        } catch (DateTimeParseException e) {
            throw new DukeException(ExceptionTypeEnum.INCORRECT_EVENT_DATE);
        }
        Task task = new EventTask(description, at);
        return new AddCommand(task);
    }

    private static AddCommand parseDeadline(String remainingText) throws DukeException {
        if (remainingText == null) {
            throw new DukeException(ExceptionTypeEnum.MISSING_DEADLINE_DESCRIPTION);
        }
        String[] taskItems = remainingText.split(" /by ");
        String description = taskItems[0].trim();
        if (taskItems.length == 1) {
            throw new DukeException(ExceptionTypeEnum.MISSING_DEADLINE_DATE);
        }

        LocalDate by;
        try {
            by = LocalDate.parse(taskItems[1].trim());
        } catch (DateTimeParseException e) {
            throw new DukeException(ExceptionTypeEnum.INCORRECT_DEADLINE_DATE);
        }
        Task task = new DeadlineTask(description, by);
        return new AddCommand(task);
    }

    private static AddCommand parseNote(String remainingText) throws DukeException {
        if (remainingText == null) {
            throw new DukeException(ExceptionTypeEnum.MISSING_NOTE_NAME);
        }
        String[] taskItems = remainingText.split(" /desc ");
        String name = taskItems[0].trim();
        if (taskItems.length == 1) {
            throw new DukeException(ExceptionTypeEnum.MISSING_NOTE_DESCRIPTION);
        }
        String description = taskItems[1].trim();
        Task task = new Note(description, name);
        return new AddCommand(task);
    }

    private static DoneCommand parseDoneCommand(String remainingText) throws DukeException {
        if (remainingText == null) {
            throw new DukeException(ExceptionTypeEnum.MISSING_DONE_ITEM);
        }
        int index = Integer.parseInt(remainingText) - 1;
        return new DoneCommand(index);
    }

    private static DeleteCommand parseDeleteCommand(String remainingText) throws DukeException {
        if (remainingText == null) {
            throw new DukeException(ExceptionTypeEnum.MISSING_DELETE_ITEM);
        }
        int index = Integer.parseInt(remainingText) - 1;
        return new DeleteCommand(index);
    }

    private static ByeCommand parseByeCommand(String remainingText) throws DukeException {
        if (remainingText != null) {
            throw new DukeException(ExceptionTypeEnum.INCORRECT_BYE);
        }
        return new ByeCommand();
    }

    private static String getMappedCommand(String command) {
        commandMap.put("list", "list");
        commandMap.put("l", "list");
        commandMap.put("todo", "todo");
        commandMap.put("t", "todo");
        commandMap.put("deadline", "deadline");
        commandMap.put("d", "deadline");
        commandMap.put("event", "event");
        commandMap.put("e", "event");
        commandMap.put("note", "note");
        commandMap.put("n", "note");
        commandMap.put("done", "done");
        commandMap.put("delete", "delete");
        commandMap.put("-d", "delete");
        commandMap.put("find", "find");
        commandMap.put("f", "find");
        commandMap.put("view", "view");
        commandMap.put("v", "view");
        commandMap.put("bye", "bye");
        commandMap.put("x", "bye");
        return Optional.ofNullable(commandMap.get(command)).orElse("unknown");
    }
}
