package duke.utility;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.ListCommand;
import duke.command.TaskAfterCommand;
import duke.command.TaskBeforeCommand;
import duke.command.ToDoCommand;
import duke.exception.DeadlineException;
import duke.exception.DeleteException;
import duke.exception.DoneException;
import duke.exception.DukeException;
import duke.exception.EventException;
import duke.exception.FindException;
import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidDateTimeFormatException;
import duke.exception.InvalidTaskNumberException;
import duke.exception.NotACommandException;
import duke.exception.ToDoException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;

/**
 * Class to parse the user input. This class will try to change the user input to a functional command.
 */
public class Parser {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /**
     * Parses the task so that it will be saved in the specific format
     * in the hard disk.
     * @param task Task to be parsed
     * @return parsed task for saving purpose
     */
    public static String parseForSave(Task task) {
        String taskName = task.getTaskName();
        String parsed = null;
        String isDone = task.isDone() ? "1" : "0";

        if (task instanceof ToDoTask) {
            parsed = "T | " + isDone + " | " + taskName + "\n";
        } else if (task instanceof DeadlineTask) {
            DeadlineTask deadlineTask = (DeadlineTask) task;
            String date = deadlineTask.getDateString();
            parsed = "D | " + isDone + " | " + taskName + " | " + date + "\n";
        } else if (task instanceof EventTask) {
            EventTask eventTask = (EventTask) task;
            String time = eventTask.getDateString();
            parsed = "E | " + isDone + " | " + taskName + " | " + time + "\n";
        }

        return parsed;
    }

    /**
     * Parses the string that is being read in the hard disk and creates the task representation of the string.
     * @param taskString String that represents a task in the hard disk
     * @return The task representation of the string
     */
    public static Task parseForReadingFile(String taskString) {
        String[] taskArr = taskString.split("\\|");

        String taskType = taskArr[0].trim();
        boolean isDone = taskArr[1].trim().equals("1");
        String taskName = taskArr[2].trim();

        Task newTask = null;

        switch (taskType) {
        case "T":
            newTask = new ToDoTask(taskName);
            break;
        case "D":
            String dateForDeadline = taskArr[3].trim();
            LocalDateTime deadlineDate = LocalDateTime.parse(dateForDeadline, FORMATTER);
            newTask = new DeadlineTask(taskName, deadlineDate);
            break;
        case "E":
            String dateForEvent = taskArr[3].trim();
            LocalDateTime eventDate = LocalDateTime.parse(dateForEvent, FORMATTER);
            newTask = new EventTask(taskName, eventDate);
            break;
        default:
            break;
        }

        if (isDone) {
            newTask.setStatusToDone();
        }

        return newTask;
    }

    /**
     * Parses the user input in order to change it into a functional command.
     * @param userInput The user input
     * @return The command representation of the user input
     * @throws DukeException If there is no arg or wrong arg for
     * specific command or user input wrong command
     */
    public static Command parseUserInput(String userInput) throws DukeException {
        String[] userInputArr = userInput.split("\\s", 2);

        String command = userInputArr[0];
        String arg = null;

        if (userInputArr.length != 1) {
            arg = userInputArr[1];
        }

        if (command.equals(Command.getCommandBye())) {
            return new ExitCommand();
        } else if (command.equals(Command.getCommandList())) {
            return new ListCommand();
        } else if (command.equals(Command.getCommandDone())) {
            if (arg == null) {
                throw new DoneException();
            }

            int taskNumber = parseStringToNumber(arg);

            return new DoneCommand(taskNumber);
        } else if (command.equals(Command.getCommandDelete())) {
            if (arg == null) {
                throw new DeleteException();
            }

            int taskNumber = parseStringToNumber(arg);

            return new DeleteCommand(taskNumber);
        } else if (command.equals(Command.getCommandTaskAfter())) {
            if (arg == null) {
                throw new InvalidDateFormatException();
            }

            LocalDate parsedDate = parseLocalDate(arg);

            return new TaskAfterCommand(parsedDate);
        } else if (command.equals(Command.getCommandTaskBefore())) {
            if (arg == null) {
                throw new InvalidDateFormatException();
            }

            LocalDate parsedDate = parseLocalDate(arg);

            return new TaskBeforeCommand(parsedDate);
        } else if (command.equals(Command.getCommandTodo())) {
            if (arg == null) {
                throw new ToDoException();
            }

            return new ToDoCommand(arg);
        } else if (command.equals(Command.getCommandDeadline())) {
            if (arg == null) {
                throw new DeadlineException();
            }

            String[] arrForDeadline = arg.split("/by", 2);

            if (arrForDeadline.length == 1) {
                throw new DeadlineException();
            }

            String taskForDeadline = arrForDeadline[0].trim();
            String dateForDeadline = arrForDeadline[1].trim();

            LocalDateTime deadlineDate = parseLocalDateTime(dateForDeadline);

            return new DeadlineCommand(taskForDeadline, deadlineDate);
        } else if (command.equals(Command.getCommandEvent())) {
            if (arg == null) {
                throw new EventException();
            }

            String[] arrForEvent = arg.split("/at", 2);

            if (arrForEvent.length == 1) {
                throw new EventException();
            }

            String taskForEvent = arrForEvent[0].trim();
            String dateForEvent = arrForEvent[1].trim();

            LocalDateTime eventDate = parseLocalDateTime(dateForEvent);

            return new EventCommand(taskForEvent, eventDate);
        } else if (command.equals(Command.getCommandFind())) {
            if (arg == null) {
                throw new FindException();
            }

            return new FindCommand(arg);
        } else {
            throw new NotACommandException();
        }
    }

    private static int parseStringToNumber(String stringNumber) throws DukeException {
        try {
            return Integer.parseInt(stringNumber);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    private static LocalDate parseLocalDate(String stringDate) throws DukeException {
        try {
            return LocalDate.parse(stringDate);
        } catch (DateTimeParseException e) {
            throw new InvalidDateFormatException();
        }
    }

    private static LocalDateTime parseLocalDateTime(String stringDateTime) throws DukeException {
        try {
            return LocalDateTime.parse(stringDateTime, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeFormatException();
        }
    }
}
