package duke.utility;

import duke.command.Command;
import duke.command.DeadlineCommand;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.EventCommand;
import duke.command.ExitCommand;
import duke.command.ListCommand;
import duke.command.TaskAfterCommand;
import duke.command.TaskBeforeCommand;
import duke.command.ToDoCommand;
import duke.exception.DeadlineException;
import duke.exception.DeleteException;
import duke.exception.DoneException;
import duke.exception.DukeException;
import duke.exception.EventException;
import duke.exception.InvalidDateFormatException;
import duke.exception.InvalidDateInputException;
import duke.exception.InvalidTaskNumberException;
import duke.exception.NotACommandException;
import duke.exception.ToDoException;
import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static String parseForSave(Task task) {
        String taskName = task.getTaskName();
        String isDone = task.isDone() ? "1" : "0";

        String parsed = null;

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

    public static Task parseForReadingFile(String taskString) {
        String[] taskArr = taskString.split("\\|");

        String taskType = taskArr[0].trim();
        boolean isDone = taskArr[1].trim().equals("1");
        String taskName = taskArr[2].trim();

        Task newTask = null;

        if (taskType.equals("T")) {
            newTask = new ToDoTask(taskName);
        } else if (taskType.equals("D")) {
            String date = taskArr[3].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime deadlineDate = LocalDateTime.parse(date, formatter);
            newTask = new DeadlineTask(taskName, deadlineDate);
        } else if (taskType.equals("E")) {
            String date = taskArr[3].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime eventDate = LocalDateTime.parse(date, formatter);
            newTask = new EventTask(taskName, eventDate);
        }

        if (isDone) {
            newTask.setStatusToDone();
        }

        return newTask;
    }

    public static Command parseUserInput(String userInput) throws DukeException {
        String[] userInputArr = userInput.split("\\s", 2);
        String command = userInputArr[0];
        String arg = null;


        if (userInputArr.length != 1) {
            arg = userInputArr[1];
        }

        if (command.equals(Command.BYE_COMMAND)) {
            return new ExitCommand();
        } else if (command.equals(Command.LIST_COMMAND)) {
            return new ListCommand();
        } else if (command.equals(Command.DONE_COMMAND)) {
            int taskNumber;
            try {
                if (arg == null) {
                    throw new DoneException();
                }

                taskNumber = Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                throw new InvalidTaskNumberException();
            }
            return new DoneCommand(taskNumber);
        } else if (command.equals(Command.DELETE_COMMAND)) {
            int taskNumber;
            try {
                if (arg == null) {
                    throw new DeleteException();
                }

                taskNumber = Integer.parseInt(arg);
            } catch (NumberFormatException e) {
                throw new InvalidTaskNumberException();
            }
            return new DeleteCommand(taskNumber);
        } else if (command.equals(Command.TASK_AFTER_COMMAND)) {
            if (arg == null) {
                throw new InvalidDateFormatException();
            }

            LocalDate parsedDate;

            try {
                parsedDate = LocalDate.parse(arg);
            } catch (DateTimeParseException e) {
                throw new InvalidDateFormatException();
            }

            return new TaskAfterCommand(parsedDate);
        } else if (command.equals(Command.TASK_BEFORE_COMMAND)) {
            if (arg == null) {
                throw new InvalidDateFormatException();
            }

            LocalDate parsedDate;
            try {
                parsedDate = LocalDate.parse(arg);
            } catch (DateTimeParseException e) {
                throw new InvalidDateFormatException();
            }

            return new TaskBeforeCommand(parsedDate);
        } else if (command.equals(Command.TODO_COMMAND)) {
            if (arg == null) {
                throw new ToDoException();
            }
            return new ToDoCommand(arg);
        } else if (command.equals(Command.DEADLINE_COMMAND)) {
            if (arg == null) {
                throw new DeadlineException();
            }

            String[] arrForDeadline = arg.split("/by", 2);

            if (arrForDeadline.length == 1) {
                throw new DeadlineException();
            }

            String taskForDeadline = arrForDeadline[0].trim();
            String dateForDeadline = arrForDeadline[1].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime deadlineDate;

            try {
                deadlineDate = LocalDateTime.parse(dateForDeadline, formatter);
            } catch (DateTimeParseException e) {
                throw new InvalidDateInputException();
            }

            return new DeadlineCommand(taskForDeadline, deadlineDate);
        } else if (command.equals(Command.EVENT_COMMAND)) {
            if (arg == null) {
                throw new EventException();
            }
            String[] arrForEvent = arg.split("/at", 2);

            if (arrForEvent.length == 1) {
                throw new EventException();
            }

            String taskForEvent = arrForEvent[0].trim();
            String dateForEvent = arrForEvent[1].trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
            LocalDateTime eventDate;

            try {
                eventDate = LocalDateTime.parse(dateForEvent, formatter);
            } catch (DateTimeParseException e) {
                throw new InvalidDateInputException();
            }

            return new EventCommand(taskForEvent, eventDate);
        } else {
            throw new NotACommandException();
        }
    }
}
