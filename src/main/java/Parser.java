import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {

    public static void parseInput(String input, Ui ui, TaskList tasks, Storage storage) throws DukeException, IOException {
        String[] parsed = input.split(" ", 2);
        String keyword = parsed[0];
        String body = "";
        if (parsed.length > 1) {
            body = parsed[1];
        }
        switch (keyword) {
        case "list":
            tasks.printList(ui);
            break;
        case "done":
            if (isValidSize(body, tasks)) {
                tasks.markTaskDone(getNumber(body), ui);
            } else {
                throw new DukeException("Invalid number");
            }
            break;
        case "delete":
            if (isValidSize(body, tasks)) {
                tasks.deleteTask(getNumber(body), ui);
            } else {
                throw new DukeException("Invalid number");
            }
            break;
        case "todo":
            if (isValidFormat(body, Task.Type.TODO)) {
                tasks.addTask(new Todo(body), ui);
            } else {
                throw new DukeException("Invalid task format");
            }
            break;
        case "deadline":
            if (isValidFormat(body, Task.Type.DEADLINE) && isValidTime(body, Task.Type.DEADLINE)) {
                tasks.addTask(new Deadline(getDescription(body), getTime(body, Task.Type.DEADLINE)), ui);
            } else {
                throw new DukeException("Invalid task format");
            }
            break;
        case "event":
            if (isValidFormat(body, Task.Type.EVENT) && isValidTime(body, Task.Type.EVENT)) {
                tasks.addTask(new Event(getDescription(body), getTime(body, Task.Type.EVENT)), ui);
            } else {
                throw new DukeException("Invalid task format");
            }
            break;
        case "find":
            tasks.findTask(body, ui);
        case "bye":
            break;
        default:
            throw new DukeException("Invalid command");
        }
        storage.writeFile(tasks);
    }

    public static boolean isBye(String input){
        return input.split(" ", 2)[0].equals("bye");
    }

    private static boolean isValidSize(String body, TaskList tasks) {
        if (body.length() > 0) {
            String num = body;
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

    private static int getNumber(String body) {
        String num = body.split(" ", 2)[0];
        return Integer.parseInt(num);
    }

    private static String getDescription(String body) {
        return body.split(" /", 2)[0];
    }

    private static boolean isValidFormat(String body, Task.Type type) {
        if (type == Task.Type.TODO) {
            return !body.equals("");
        }
        if (type == Task.Type.DEADLINE) {
            return body.split(" /by ", 2).length == 2;
        }
        if (type == Task.Type.EVENT) {
            return body.split(" /at ", 2).length == 2;
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
}