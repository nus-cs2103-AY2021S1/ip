package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import duke.backend.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * Responsible for parsing the user's input and processing the desired command.
 */
public class Parser {
    // A list of all commands recognised by Duke.
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String FIND = "find";
    private static final String HELP = "help";
    private static final String SNOOZE = "snooze";

    // Special Strings used by duke.Parser.
    private static final String SPACE = " ";
    private static final String EMPTY_STRING = "";
    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_SEPARATOR = " /at ";
    private static final String SEPARATOR = " /";

    // For time formatting.
    private static final String NOW = "now";

    /**
     * Parses the user input and process the user's command.
     * @param input User input.
     * @param ui User interface.
     * @param tasks TaskList.
     * @param storage Storage.
     * @return A String describing the outcome after processing the user's command.
     * @throws DukeException Any error regarding Duke.
     * @throws IOException When storage faces any error.
     */
    public static String parseInput(String input, Ui ui, TaskList tasks, Storage storage)
            throws DukeException, IOException {
        String[] parsedInput = input.split(SPACE, 2);
        String commandKeyword = parsedInput[0].toLowerCase();
        if (commandKeyword.equals(BYE)) {
            return handleByeCommand(ui);
        }
        if (commandKeyword.equals(LIST)) {
            return handleListCommand(ui, tasks);
        }
        if (commandKeyword.equals(DONE)) {
            return handleDoneCommand(ui, tasks, storage, parsedInput);
        }
        if (commandKeyword.equals(DELETE)) {
            return handleDeleteCommand(ui, tasks, storage, parsedInput);
        }
        if (commandKeyword.equals(TODO)) {
            return handleTodoCommand(ui, tasks, storage, parsedInput);
        }
        if (commandKeyword.equals(DEADLINE)) {
            return handleDeadlineCommand(ui, tasks, storage, parsedInput);
        }
        if (commandKeyword.equals(EVENT)) {
            return handleEventCommand(ui, tasks, storage, parsedInput);
        }
        if (commandKeyword.equals(FIND)) {
            return handleFindCommand(ui, tasks, parsedInput);
        }
        if (commandKeyword.equals(HELP)) {
            return handleHelpCommand(ui);
        }
        if (commandKeyword.equals(SNOOZE)) {
            return handleSnoozeCommand(ui, tasks, storage, parsedInput);
        }
        // The user's command keyword is not recognised.
        throw new DukeException("Invalid command keyword");
    }

    /**
     * Checks if user input is bye.
     * @param input User input.
     * @return A boolean stating if command is bye.
     */
    public static boolean isBye(String input) {
        String commandKeyword = input.split(SPACE, 2)[0].toLowerCase();
        return commandKeyword.equals(BYE);
    }

    // ===== PRIVATE HANDLER AND HELPER METHODS =====

    private static String handleByeCommand(Ui ui) {
        return ui.sayGoodbye();
    }

    private static String handleHelpCommand(Ui ui) {
        return ui.giveHelp();
    }

    private static String handleListCommand(Ui ui, TaskList tasks) {
        return tasks.getList(ui);
    }

    private static String handleDoneCommand(Ui ui, TaskList tasks, Storage storage, String[] parsedInput)
            throws DukeException, IOException {
        if (isValidSize(tasks, parsedInput)) {
            int taskNumber = getNumber(parsedInput);
            assert taskNumber > 0 : "Number should be greater than 0";
            return tasks.markTaskDone(ui, taskNumber, storage);
        } else {
            throw new DukeException("Invalid number for DONE command");
        }
    }

    private static String handleDeleteCommand(Ui ui, TaskList tasks, Storage storage, String[] parsedInput)
            throws DukeException, IOException {
        if (isValidSize(tasks, parsedInput)) {
            int taskNumber = getNumber(parsedInput);
            assert taskNumber > 0 : "Number should be greater than 0";
            return tasks.deleteTask(ui, taskNumber, storage);
        } else {
            throw new DukeException("Invalid number for DELETE command");
        }
    }

    private static boolean isValidSize(TaskList tasks, String[] parsedInput) {
        boolean hasDescription = parsedInput.length > 1;
        if (!hasDescription) {
            return false;
        }
        String num = parsedInput[1];
        try {
            int number = Integer.parseInt(num);
            return tasks.getListSize() >= number && number > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static int getNumber(String[] parsedInput) {
        String numberString = parsedInput[1];
        return Integer.parseInt(numberString);
    }

    private static String handleTodoCommand(Ui ui, TaskList tasks, Storage storage, String[] parsedInput)
            throws DukeException, IOException {
        if (isValidFormat(parsedInput, Task.Type.TODO)) {
            Todo newTodo = getTodo(parsedInput);
            return tasks.addTask(newTodo, ui, storage);
        } else {
            throw new DukeException("Invalid TODO format");
        }
    }

    private static String handleDeadlineCommand(Ui ui, TaskList tasks, Storage storage, String[] parsedInput)
            throws DukeException, IOException {
        if (isValidFormat(parsedInput, Task.Type.DEADLINE)) {
            Deadline newDeadline = getDeadline(parsedInput);
            return tasks.addTask(newDeadline, ui, storage);
        } else {
            throw new DukeException("Invalid DEADLINE format");
        }
    }

    private static String handleEventCommand(Ui ui, TaskList tasks, Storage storage, String[] parsedInput)
            throws DukeException, IOException {
        if (isValidFormat(parsedInput, Task.Type.EVENT)) {
            Event newEvent = getEvent(parsedInput);
            return tasks.addTask(newEvent, ui, storage);
        } else {
            throw new DukeException("Invalid EVENT format");
        }
    }

    private static String handleFindCommand(Ui ui, TaskList tasks, String[] parsedInput)
            throws DukeException {
        if (isValidFindFormat(parsedInput)) {
            return tasks.findTask(ui, parsedInput);
        } else {
            throw new DukeException("Invalid FIND format");
        }
    }

    private static boolean isValidFindFormat(String[] parsedInput) {
        return parsedInput.length > 1;
    }

    private static Todo getTodo(String[] parsedInput) {
        return new Todo(parsedInput[1]);
    }

    private static Deadline getDeadline(String[] parsedInput) throws DukeException {
        String description = getDescription(parsedInput[1]);
        LocalDate time = getTime(parsedInput[1], Task.Type.DEADLINE);
        return new Deadline(description, time);
    }

    private static Event getEvent(String[] parsedInput) throws DukeException {
        String description = getDescription(parsedInput[1]);
        LocalDate time = getTime(parsedInput[1], Task.Type.EVENT);
        return new Event(description, time);
    }

    private static String getDescription(String body) {
        return body.split(SEPARATOR, 2)[0];
    }

    private static LocalDate getTime(String body, Task.Type type) throws DukeException {
        String time;
        if (type == Task.Type.DEADLINE) {
            time = body.split(DEADLINE_SEPARATOR, 2)[1];
        } else if (type == Task.Type.EVENT) {
            time = body.split(EVENT_SEPARATOR, 2)[1];
        } else {
            throw new DukeException("Unidentified task type");
        }
        if (time.toLowerCase().equals(NOW)) {
            return LocalDate.now();
        } else {
            return LocalDate.parse(time);
        }
    }

    private static boolean isValidFormat(String[] parsedInput, Task.Type type) throws DukeException {
        boolean hasDescription = parsedInput.length > 1;
        if (!hasDescription) {
            return false;
        }
        String body = parsedInput[1];
        if (type == Task.Type.TODO) {
            return !body.equals(EMPTY_STRING);
        }
        if (type == Task.Type.DEADLINE) {
            return body.split(DEADLINE_SEPARATOR, 2).length == 2 && isValidTime(body, Task.Type.DEADLINE);
        }
        if (type == Task.Type.EVENT) {
            return body.split(EVENT_SEPARATOR, 2).length == 2 && isValidTime(body, Task.Type.EVENT);
        }
        return false;
    }

    private static boolean isValidTime(String body, Task.Type type) throws DateTimeParseException, DukeException {
        String time;
        if (type == Task.Type.DEADLINE) {
            time = body.split(DEADLINE_SEPARATOR, 2)[1];
        } else if (type == Task.Type.EVENT) {
            time = body.split(EVENT_SEPARATOR, 2)[1];
        } else {
            throw new DukeException("Unidentified task type");
        }
        if (time.toLowerCase().equals(NOW)) {
            return true;
        }
        try {
            LocalDate.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    private static String handleSnoozeCommand(Ui ui, TaskList tasks, Storage storage, String[] parsedInput)
            throws DukeException, IOException {
        if (isValidSize(tasks, parsedInput)) {
            int taskNumber = getNumber(parsedInput);
            assert taskNumber > 0 : "Number should be greater than 0";
            return tasks.snoozeTask(ui, taskNumber, storage);
        } else {
            throw new DukeException("Invalid number for SNOOZE command");
        }
    }
}
