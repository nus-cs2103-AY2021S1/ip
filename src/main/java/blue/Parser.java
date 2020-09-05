package blue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import blue.command.ByeCommand;
import blue.command.Command;
import blue.command.DeleteCommand;
import blue.command.DoneCommand;
import blue.command.FindDateCommand;
import blue.command.FindDescriptionCommand;
import blue.command.ListCommand;
import blue.command.TaskCommand;
import blue.exception.BlueException;
import blue.exception.EmptyInputException;
import blue.exception.EmptyTaskException;
import blue.exception.InvalidInputException;
import blue.exception.InvalidTaskException;
import blue.exception.UnknownInputException;
import blue.task.Deadline;
import blue.task.Event;
import blue.task.TaskType;
import blue.task.Todo;

/**
 * The Parser deals with making sense of the user command.
 */
public class Parser {

    /**
     * The constant minStringLength.
     */
    private static final int minStringLength = 1;

    /**
     * Returns the command after parsing the input.
     *
     * @param input the input.
     * @return Command the command.
     * @throws BlueException If the input cannot be parsed.
     */
    public static Command parse(String input) throws BlueException {
        String keyword = input.split(" ")[0];
        switch (keyword) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand();
        case "done":
            return getDoneCommand(input);
        case "delete":
            return getDeleteCommand(input);
        case "todo":
            return getTodoTaskCommand(input);
        case "event":
            return getEventTaskCommand(input);
        case "deadline":
            return getDeadlineTaskCommand(input);
        case "date":
            return getFindDateCommand(input);
        case "find":
            return getFindCommand(input);
        case "today":
            return getTodayCommand();
        default:
            throw new UnknownInputException();
        }
    }

    /**
     * Returns done command.
     *
     * @param input the input.
     * @return the done command.
     * @throws EmptyInputException   If the task number is not in input.
     * @throws UnknownInputException If the task number cannot be parsed.
     */
    private static DoneCommand getDoneCommand(String input) throws EmptyInputException,
            UnknownInputException {
        String taskIndexStr;
        try {
            taskIndexStr = input.substring(5).trim();
        } catch (IndexOutOfBoundsException ex) {
            throw new EmptyInputException("The task to be marked as done is not specified.");
        }

        if (taskIndexStr.length() < minStringLength) {
            throw new EmptyInputException("The task to be marked as done is not specified.");
        }

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskIndexStr) - 1;
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            throw new UnknownInputException();
        }

        return new DoneCommand(taskIndex);
    }

    /**
     * Returns delete command.
     *
     * @param input the input.
     * @return the delete command.
     * @throws EmptyInputException   If the task number is not in input.
     * @throws UnknownInputException If the task number cannot be parsed.
     */
    private static DeleteCommand getDeleteCommand(String input) throws EmptyInputException,
            UnknownInputException {
        String taskIndexStr;
        try {
            taskIndexStr = input.substring(7).trim();
        } catch (IndexOutOfBoundsException ex) {
            throw new EmptyInputException("The task to be deleted is not specified.");
        }

        if (taskIndexStr.length() < minStringLength) {
            throw new EmptyInputException("The task to be deleted is not specified.");
        }

        int taskIndex;
        try {
            taskIndex = Integer.parseInt(taskIndexStr) - 1;
        } catch (IndexOutOfBoundsException | NumberFormatException ex) {
            throw new UnknownInputException();
        }

        return new DeleteCommand(taskIndex);
    }

    /**
     * Returns task command with todo.
     *
     * @param input the input.
     * @return the task command with todo.
     * @throws EmptyTaskException If the description in input is empty.
     */
    private static TaskCommand getTodoTaskCommand(String input) throws EmptyTaskException {
        if (input.length() == 4) {
            throw new EmptyTaskException("description", TaskType.TODO);
        }

        String description = input.substring(5).trim();
        if (description.length() < minStringLength) {
            throw new EmptyTaskException("description", TaskType.TODO);
        }

        Todo todo = new Todo(description);
        return new TaskCommand(todo);
    }

    /**
     * Returns task command with event.
     *
     * @param input the input.
     * @return the task command with event.
     * @throws EmptyTaskException   If the description or date in input is empty.
     * @throws InvalidTaskException If the /at command is not in input or the date cannot be parsed.
     */
    private static TaskCommand getEventTaskCommand(String input) throws EmptyTaskException,
            InvalidTaskException {
        int currStrIndex = 6;
        int slashIndex = input.indexOf("/at");
        if (slashIndex == -1) {
            throw new InvalidTaskException("The /at command couldn't be found.");
        }

        String description = input.substring(currStrIndex, slashIndex).trim();
        if (description.length() < minStringLength) {
            throw new EmptyTaskException("description", TaskType.EVENT);
        }
        currStrIndex = slashIndex + 3;

        String dateStr;
        try {
            dateStr = input.substring(currStrIndex, currStrIndex + 11).trim();
        } catch (IndexOutOfBoundsException ex) {
            throw new EmptyTaskException("date", TaskType.EVENT);
        }
        currStrIndex += 11;

        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dateStr);
        } catch (DateTimeParseException ex) {
            String message = "The date of event is invalid, it should be in YYYY-MM-DD format.";
            throw new InvalidTaskException(message);
        }

        String startTimeStr;
        try {
            startTimeStr = input.substring(currStrIndex, currStrIndex + 8).trim();
        } catch (IndexOutOfBoundsException ex) {
            throw new EmptyTaskException("start time", TaskType.EVENT);
        }
        currStrIndex += 9;

        LocalTime startTimeLocal;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mma");
        try {
            startTimeLocal = LocalTime.parse(startTimeStr, dateTimeFormatter);
        } catch (DateTimeParseException ex) {
            String message = "The start time of the event is invalid, it should be in HH:MMAA format.";
            throw new InvalidTaskException(message);
        }

        if (input.length() < currStrIndex) {
            throw new EmptyTaskException("end time", TaskType.EVENT);
        }
        String endTimeStr = input.substring(currStrIndex).trim();
        if (endTimeStr.length() < minStringLength) {
            throw new EmptyTaskException("end time", TaskType.EVENT);
        }

        LocalTime endTimeLocal;
        try {
            endTimeLocal = LocalTime.parse(endTimeStr, dateTimeFormatter);
        } catch (DateTimeParseException ex) {
            String message = "The end time of the event is invalid, it should be in HH:MMAA format.";
            throw new InvalidTaskException(message);
        }

        if (startTimeLocal.compareTo(endTimeLocal) > 0) {
            String message = "The end time of the event is invalid, it should be after the start time.";
            throw new InvalidTaskException(message);
        }

        Event event = new Event(description, localDate, startTimeLocal, endTimeLocal);
        return new TaskCommand(event);
    }

    /**
     * Returns task command with deadline.
     *
     * @param input the input.
     * @return the deadline task command.
     * @throws EmptyTaskException   If the description or date in input is empty.
     * @throws InvalidTaskException If the /by command is not in input or the date cannot be parsed.
     */
    private static Command getDeadlineTaskCommand(String input) throws EmptyTaskException,
            InvalidTaskException {
        int slashIndex = input.indexOf("/by");
        if (slashIndex == -1) {
            throw new InvalidTaskException("The /by command couldn't be found.");
        }

        String description = input.substring(9, slashIndex).trim();
        if (description.length() < minStringLength) {
            throw new EmptyTaskException("description", TaskType.DEADLINE);
        }

        String deadlineStr = input.substring(slashIndex + 3).trim();
        if (deadlineStr.length() < minStringLength) {
            throw new EmptyTaskException("date", TaskType.DEADLINE);
        }

        LocalDate localDeadline;
        try {
            localDeadline = LocalDate.parse(deadlineStr.trim());
        } catch (DateTimeParseException ex) {
            String message = "The deadline of deadline is invalid, it should be in YYYY-MM-DD format.";
            throw new InvalidTaskException(message);
        }

        Deadline deadline = new Deadline(description, localDeadline);
        return new TaskCommand(deadline);
    }

    /**
     * Returns find date command.
     *
     * @param input the input.
     * @return the find date command.
     * @throws EmptyInputException   If the date is not in input.
     * @throws InvalidInputException If the date cannot be parsed.
     */
    private static FindDateCommand getFindDateCommand(String input) throws EmptyInputException,
            InvalidInputException {
        String dateStr;
        try {
            dateStr = input.substring(5).trim();
        } catch (IndexOutOfBoundsException ex) {
            throw new EmptyInputException("The date to be searched is not specified.");
        }

        if (dateStr.length() < minStringLength) {
            throw new EmptyInputException("The date to be searched is not specified.");
        }

        LocalDate localDate;
        try {
            localDate = LocalDate.parse(dateStr.trim());
        } catch (DateTimeParseException ex) {
            String message = "The date to be searched is invalid, it should be in YYYY-MM-DD format.";
            throw new InvalidInputException(message);
        }

        return new FindDateCommand(localDate);
    }

    /**
     * Returns find description command.
     *
     * @param input the input.
     * @return the find description command.
     * @throws EmptyInputException If the description is not in input.
     */
    private static FindDescriptionCommand getFindCommand(String input) throws EmptyInputException {
        String itemStr;
        try {
            itemStr = input.substring(5).trim();
        } catch (IndexOutOfBoundsException ex) {
            throw new EmptyInputException("The task to be searched is not specified.");
        }

        if (itemStr.length() < minStringLength) {
            throw new EmptyInputException("The task to be searched is not specified.");
        }

        return new FindDescriptionCommand(itemStr);
    }

    /**
     * Returns find date command with today as date.
     *
     * @return the find date command.
     */
    private static FindDateCommand getTodayCommand() {
        return new FindDateCommand(LocalDate.now());
    }
}
