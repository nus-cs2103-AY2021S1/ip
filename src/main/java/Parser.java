import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Responsible for parsing the user's input and processing the desired command.
 */
public class Parser {
    private static final String BYE = "bye";
    private static final String LIST = "list";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private static final String FIND = "find";

    private static final String DEADLINE_SEPARATOR = " /by ";
    private static final String EVENT_SEPARATOR = " /at ";

    /**
     *
     */
    public static String parseInput(String input, Ui ui, TaskList tasks, Storage storage) throws DukeException, IOException {
        String[] parsedInput = input.split(" ", 2);
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
        throw new DukeException("Invalid command keyword");
    }

    private static String handleByeCommand(Ui ui) {
        return ui.sayGoodbye();
    }

    private static String handleListCommand(Ui ui, TaskList tasks) {
        return tasks.getList(ui);
    }

    private static String handleDoneCommand(Ui ui, TaskList tasks, Storage storage, String[] parsedInput) throws DukeException, IOException {
        if (isValidSize(tasks, parsedInput)) {
            int taskNumber = getNumber(parsedInput);
            return tasks.markTaskDone(ui, taskNumber, storage);
        } else {
            throw new DukeException("Invalid number");
        }
    }

    private static String handleDeleteCommand(Ui ui, TaskList tasks, Storage storage, String[] parsedInput) throws DukeException, IOException {
        if (isValidSize(tasks, parsedInput)) {
            int taskNumber = getNumber(parsedInput);
            return tasks.deleteTask(ui, taskNumber, storage);
        } else {
            throw new DukeException("Invalid number");
        }
    }

    private static String handleTodoCommand(Ui ui, TaskList tasks, Storage storage, String[] parsedInput) throws DukeException, IOException {
        if (isValidFormat(parsedInput, Task.Type.TODO)) {
            Todo newTodo = getTodo(parsedInput);
            return tasks.addTask(newTodo, ui, storage);
        } else {
            throw new DukeException("Invalid TODO format");
        }
    }

    private static String handleDeadlineCommand(Ui ui, TaskList tasks, Storage storage, String[] parsedInput) throws DukeException, IOException {
        if (isValidFormat(parsedInput, Task.Type.DEADLINE)) {
            Deadline newDeadline = getDeadline(parsedInput);
            return tasks.addTask(newDeadline, ui, storage);
        } else {
            throw new DukeException("Invalid DEADLINE format");
        }
    }

    private static String handleEventCommand(Ui ui, TaskList tasks, Storage storage, String[] parsedInput) throws DukeException, IOException {
        if (isValidFormat(parsedInput, Task.Type.EVENT)) {
            Event newEvent = getEvent(parsedInput);
            return tasks.addTask(newEvent, ui, storage);
        } else {
            throw new DukeException("Invalid EVENT format");
        }
    }

    private static String handleFindCommand(Ui ui, TaskList tasks, String[] parsedInput) {
        return tasks.findTask(ui, parsedInput);
    }

    /**
     * Reads the user input and return it.
     *
     * @param input An input String.
     * @return A boolean whether input is bye.
     */
    public static boolean isBye(String input){
        String commandKeyword = input.split(" ", 2)[0].toLowerCase();
        return commandKeyword.equals("bye");
    }

    private static boolean isValidSize(TaskList tasks, String[] parsedInput) {
        if (parsedInput.length > 0) {
            String num = parsedInput[1];
            try {
                int number = Integer.parseInt(num);
                return tasks.getListSize() >= number && number > 0;
            } catch (NumberFormatException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    private static int getNumber(String[] parsedInput) {
        String numberString = parsedInput[1];
        return Integer.parseInt(numberString);
    }

    private static Todo getTodo(String[] parsedInput) {
        return new Todo(parsedInput[1]);
    }

    private static Deadline getDeadline(String[] parsedInput) {
        String description = getDescription(parsedInput[1]);
        String time = getTime(parsedInput[1], Task.Type.DEADLINE);
        return new Deadline(description, time);
    }

    private static Event getEvent(String[] parsedInput) {
        String description = getDescription(parsedInput[1]);
        String time = getTime(parsedInput[1], Task.Type.EVENT);
        return new Event(description, time);
    }

    private static String getDescription(String body) {
        return body.split(" /", 2)[0];
    }

    private static String getTime(String body, Task.Type type) {
        String time = "";
        if (type == Task.Type.DEADLINE) {
            time = body.split(" /by ", 2)[1];
        }
        if (type == Task.Type.EVENT) {
            time = body.split(" /at ", 2)[1];
        }
        if (time.equals("now")) {
            return LocalDate.now().format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } else {
            return LocalDate.parse(time).format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        }
    }

    private static boolean isValidFormat(String[] parsedInput, Task.Type type) {
        String body = parsedInput[1];
        if (type == Task.Type.TODO) {
            return !body.equals("");
        }
        if (type == Task.Type.DEADLINE) {
            return body.split(DEADLINE_SEPARATOR, 2).length == 2 && isValidTime(body, Task.Type.DEADLINE);
        }
        if (type == Task.Type.EVENT) {
            return body.split(EVENT_SEPARATOR, 2).length == 2 && isValidTime(body, Task.Type.EVENT);
        }
        return false;
    }

    private static boolean isValidTime(String body, Task.Type type) throws DateTimeParseException {
        String time = "";
        if (type == Task.Type.DEADLINE) {
            time = body.split(" /by ", 2)[1];
        }
        if (type == Task.Type.EVENT) {
            time = body.split(" /at ", 2)[1];
        }
        if (time.equals("now")) {
            return true;
        }
        try {
            LocalDate.parse(time);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}