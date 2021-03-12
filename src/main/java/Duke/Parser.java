package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import command.AddCommand;
import command.ArchiveCommand;
import command.ByeCommand;
import command.Command;
import command.DeleteCommand;
import command.DoNothingCommand;
import command.DoneCommand;
import command.FindCommand;
import command.ListCommand;
import command.TasksOnCommand;
import command.UnarchiveCommand;
import exception.DukeException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

/**
 * Represents a Parser which helps make any sense of the input from the user.
 */
public class Parser {

    /**
     * Converts the input from the user into a Command understandable by Duke.
     *
     * @param command String input from user.
     * @return A Command that Duke can execute.
     * @throws DukeException Thrown when input from user is invalid.
     */
    public static Command parse(String command) throws DukeException {
        String input = command.trim();

        if (input.equals("bye")) {
            return getByeCommand();
        }

        if (input.startsWith("done")) {
            return getDoneCommand(input);
        } else if (input.startsWith("delete")) {
            return getDeleteCommand(input);
        } else if (input.startsWith("list")) {
            return getListCommand(input);
        } else if (input.startsWith("find")) {
            return getFindCommand(input);
        } else if (input.startsWith("tasks on")) {
            return getTasksOnCommand(input);
        } else if (input.startsWith("archive")) {
            return getArchiveCommand(input);
        } else if (input.startsWith("unarchive")) {
            return getUnarchiveCommand(input);
        } else if (input.startsWith("todo")
                || input.startsWith("deadline")
                || input.startsWith("event")) {
            return getAddCommand(input);
        } else if (input.isBlank()) {
            return new DoNothingCommand();
        } else {
            throw new DukeException("I don't know what that means :-(");
        }
    }

    private static AddCommand getAddCommand(String input) throws DukeException {
        Task newTask;
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        if (input.startsWith("todo")) {
            newTask = getTodo(input);
        } else if (input.startsWith("deadline")) {
            newTask = getDeadline(input, dateFormat);
        } else if (input.startsWith("event")) {
            newTask = getEvent(input, dateFormat);
        } else {
            throw new DukeException("Unidentified Add Command");
        }

        return new AddCommand(newTask);
    }

    private static Task getEvent(String input, DateTimeFormatter dateFormat) throws DukeException {
        String eventDescription;
        LocalDateTime dateOfEvent;
        String[] inputComponents = input.split(" /at ");

        boolean hasNoDescription = inputComponents[0].length() == 5;
        boolean hasBlankDescription = hasNoDescription || inputComponents[0].substring(6).isBlank();
        if (hasBlankDescription) {
            throw new DukeException("The description of an event cannot be empty.");
        }

        boolean hasNoDate = inputComponents.length == 1;
        boolean hasBlankDate = hasNoDate || inputComponents[1].isBlank();
        if (hasBlankDate) {
            throw new DukeException("The date of an event cannot be empty.");
        }

        try {
            dateOfEvent = LocalDateTime.parse(inputComponents[1].trim(), dateFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date should be in format dd/mm/yyyy hh:mm");
        }

        eventDescription = inputComponents[0].substring(6).trim();
        return new Event(eventDescription, dateOfEvent);
    }

    private static Task getDeadline(String input, DateTimeFormatter dateFormat) throws DukeException {
        String deadlineDescription;
        LocalDateTime deadlineDate;
        String[] inputComponents = input.split(" /by ");

        boolean hasNoDescription = inputComponents[0].length() == 8;
        boolean hasBlankDescription = hasNoDescription || inputComponents[0].substring(9).isBlank();
        if (hasBlankDescription) {
            throw new DukeException("The description of a deadline cannot be empty.");
        }

        boolean hasNoDate = inputComponents.length == 1;
        boolean hasBlankDate = hasNoDate || inputComponents[1].isBlank();
        if (hasBlankDate) {
            throw new DukeException("The date of a deadline cannot be empty.");
        }

        try {
            deadlineDate = LocalDateTime.parse(inputComponents[1].trim(), dateFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date should be in format dd/mm/yyyy hh:mm");
        }

        deadlineDescription = inputComponents[0].substring(9).trim();
        return new Deadline(deadlineDescription, deadlineDate);
    }

    private static Task getTodo(String input) throws DukeException {
        String todoDescription;

        boolean hasNoDescription = input.length() == 4;
        boolean hasBlankDescription = hasNoDescription || input.substring(5).isBlank();
        if (hasBlankDescription) {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        todoDescription = input.substring(5).trim();
        return new Todo(todoDescription);
    }

    private static TasksOnCommand getTasksOnCommand(String input) throws DukeException {
        boolean hasNoDate = input.length() == 8;
        boolean hasBlankDate = hasNoDate || input.substring(9).isBlank();
        if (hasBlankDate) {
            throw new DukeException("Need to specify the date of the tasks");
        }

        String dateOfTask = input.substring(9).trim();
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date;
        try {
            date = LocalDate.parse(dateOfTask, dateFormat);
        } catch (DateTimeParseException e) {
            throw new DukeException("Date should be in format dd/mm/yyyy");
        }

        return new TasksOnCommand(date);
    }

    private static FindCommand getFindCommand(String input) throws DukeException {
        boolean hasNoKeyword = input.length() == 4;
        boolean hasBlankKeyword = hasNoKeyword || input.substring(5).isBlank();
        if (hasBlankKeyword) {
            throw new DukeException("No keyword specified.");
        }

        String keywordToFind = input.substring(5).trim();
        return new FindCommand(keywordToFind);
    }

    private static ListCommand getListCommand(String input) throws DukeException {
        boolean hasHyphenA = input.contains("-a");
        boolean hasTrailingCharacters = input.length() > 4;

        if (hasTrailingCharacters && !hasHyphenA) {
            throw new DukeException("Do you mean list -a?");
        }

        return new ListCommand(hasHyphenA);
    }

    private static DeleteCommand getDeleteCommand(String input) throws DukeException {
        boolean hasNoTaskNumber = input.length() == 6;
        boolean hasBlankTaskNumber = hasNoTaskNumber || input.substring(7).isBlank();
        if (hasBlankTaskNumber) {
            throw new DukeException("No task number specified.");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.substring(7).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Task number format invalid, "
                    + "must be a number.");
        }

        return new DeleteCommand(taskNumber);
    }

    private static Command getArchiveCommand(String input) throws DukeException {
        boolean hasNoTaskNumber = input.length() == 7;
        boolean hasBlankTaskNumber = hasNoTaskNumber || input.substring(8).isBlank();
        if (hasBlankTaskNumber) {
            throw new DukeException("No task number specified.");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.substring(8).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Task number format invalid, "
                    + "must be a number.");
        }

        return new ArchiveCommand(taskNumber);
    }

    private static Command getUnarchiveCommand(String input) throws DukeException {
        boolean hasNoTaskNumber = input.length() == 9;
        boolean hasBlankTaskNumber = hasNoTaskNumber || input.substring(10).isBlank();
        if (hasBlankTaskNumber) {
            throw new DukeException("No task number specified.");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.substring(10).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Task number format invalid, "
                    + "must be a number.");
        }

        return new UnarchiveCommand(taskNumber);
    }

    private static ByeCommand getByeCommand() {
        return new ByeCommand();
    }

    private static DoneCommand getDoneCommand(String input) throws DukeException {
        boolean hasNoTaskNumber = input.length() == 4;
        boolean hasBlankTaskNumber = hasNoTaskNumber || input.substring(5).isBlank();
        if (hasBlankTaskNumber) {
            throw new DukeException("No task number specified.");
        }

        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.substring(5).trim()) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("Task number format invalid, "
                    + "must be a number.");
        }

        return new DoneCommand(taskNumber);
    }
}
