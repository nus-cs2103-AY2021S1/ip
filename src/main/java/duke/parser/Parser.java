package duke.parser;

import duke.command.Command;
import duke.command.ByeCommand;
import duke.command.DoneCommand;
import duke.command.DeleteCommand;
import duke.command.ListCommand;
import duke.command.AddTodoCommand;
import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.RetrieveCommand;
import duke.exception.DukeException;
import duke.exception.InvalidCommandException;
import duke.exception.NoTaskException;
import duke.exception.InvalidDeleteException;
import duke.exception.InvalidDoneException;
import duke.exception.NoTaskContentException;
import duke.exception.NoTaskDurationException;
import duke.exception.InvalidTaskNumberException;
import duke.exception.InvalidTaskDateException;
import duke.exception.NoTaskDateTimeException;
import duke.tasklist.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static Command parse(String input, TaskList tasks) throws DukeException {
        if (input.equals("bye")) {
            return new ByeCommand();
        } else if (input.equals("list")) {
            return parseList(tasks);
        } else if (input.startsWith("done")) {
            return parseDone(input, tasks);
        } else if (input.startsWith("todo") || input.startsWith("deadline") || input.startsWith("event")) {
            return parseAddTask(input);
        } else if (input.startsWith("delete")) {
            return parseDelete(input, tasks);
        } else if (input.startsWith("retrieve")) {
            return parseRetrieve(input);
        } else {
            throw new InvalidCommandException();
        }
    }

    public static Command parseList(TaskList tasks) throws DukeException {
        if (tasks.getNumberOfTask() <= 0) {
            throw new NoTaskException();
        } else {
            return new ListCommand();
        }
    }

    public static Command parseDone(String input, TaskList tasks) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length != 2 || !splitInput[1].matches("[0-9]+")) {
            throw new InvalidDoneException();
        } else {
            int taskNumber = Integer.parseInt(splitInput[1]) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.getNumberOfTask()) {
                throw new InvalidTaskNumberException();
            } else {
                return new DoneCommand(taskNumber);
            }
        }
    }

    public static Command parseAddTask(String input) throws DukeException {
            String[] splitInput = input.split(" ", 2);
            String taskWord = splitInput[0];
            if (taskWord.equals("todo")) {
                if (splitInput.length != 2) {
                    throw new NoTaskContentException("OOPS!!! The description of a todo cannot be empty.");
                } else {
                    String description = splitInput[1];
                    return new AddTodoCommand(description);
                }
            } else if (taskWord.equals("deadline")) {
                if (splitInput.length != 2) {
                    throw new NoTaskContentException(
                            "OOPS!!! The description and date/time of a deadline cannot be empty.");
                } else {
                    String content = splitInput[1];
                    String[] splitContent = content.split(" /by ", 2);
                    if (splitContent.length != 2) {
                        throw new NoTaskDateTimeException(
                                "OOPS!!! The date/time of a deadline cannot be empty.");
                    } else {
                        String description = splitContent[0];
                        String by = splitContent[1];
                        return new AddDeadlineCommand(description, by);
                    }
                }
            } else if (taskWord.equals("event")) {
                if (splitInput.length != 2) {
                    throw new NoTaskContentException(
                            "OOPS!!! The description and duration of an event cannot be empty.");
                } else {
                    String content = splitInput[1];
                    String[] splitContent = content.split(" /at ", 2);
                    if (splitContent.length != 2) {
                        throw new NoTaskDurationException(
                                "OOPS!!! The duration of an event cannot be empty.");
                    } else {
                        String description = splitContent[0];
                        String at = splitContent[1];
                        return new AddEventCommand(description, at);
                    }
                }
            } else {
                throw new InvalidCommandException();
            }
    }

    public static Command parseDelete(String input, TaskList tasks) throws DukeException {
        String[] splitInput = input.split(" ");
        if (splitInput.length != 2 || !splitInput[1].matches("[0-9]+")) {
            throw new InvalidDeleteException();
        } else {
            int taskNumber = Integer.parseInt(splitInput[1]) - 1;
            if (taskNumber < 0 || taskNumber >= tasks.getNumberOfTask()) {
                throw new InvalidTaskNumberException();
            } else {
                return new DeleteCommand(taskNumber);
            }
        }
    }

    public static Command parseRetrieve(String input) throws DukeException {
        try {
            String[] splitInput = input.split(" ");
            if (splitInput.length == 1) {
                throw new NoTaskDateTimeException("OOPS!!! Please enter a date.");
            } else {
                LocalDate date = LocalDate.parse(splitInput[1], DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                return new RetrieveCommand(date);
            }
        } catch (DateTimeParseException e) {
            throw new InvalidTaskDateException();
        }
    }
}

