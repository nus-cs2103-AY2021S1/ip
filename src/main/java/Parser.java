import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static void process(String input, Ui ui, TaskList tasks, Storage storage) throws DukeException, IOException {
        String[] parsed = input.split(" ", 2);
        String keyword = parsed[0];
        String body = "";
        if (parsed.length > 1) body = parsed[1];
        switch (keyword) {
        case "list":
            tasks.printList(ui);
            break;
        case "done":
            if (isValidSize(body, tasks)) tasks.done(getNumber(body), ui);
            else throw new DukeException("Invalid number");
            break;
        case "delete":
            if (isValidSize(body, tasks)) tasks.delete(getNumber(body), ui);
            else throw new DukeException("Invalid number");
            break;
        case "todo":
            if (isValidTFormat(body)) tasks.addTask(new Todo(body), ui);
            else throw new DukeException("Invalid task format");
            break;
        case "deadline":
            if (isValidDFormat(body)) tasks.addTask(new Deadline(desc(body), deadline(body)), ui);
            else throw new DukeException("Invalid task format");
            break;
        case "event":
            if (isValidEFormat(body)) tasks.addTask(new Event(desc(body), eventTime(body)), ui);
            else throw new DukeException("Invalid task format");
            break;
        case "bye":
            break;
        default:
            throw new DukeException("Invalid command");
        }
        storage.write(tasks);
    }

    private static boolean isValidSize(String body, TaskList tasks) {
        if (body.length() > 0) {
            String num = body;
            try {
                int number = Integer.parseInt(num);
                return tasks.size() >= number && number > 0;
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

    private static String desc(String body) {
        return body.split(" /", 2)[0];
    }

    private static boolean isValidTFormat(String body) {
        return !body.equals("");
    }

    private static boolean isValidDFormat(String body) {
        return body.split(" /by ", 2).length == 2;
    }

    private static String deadline(String body) {
        String time = body.split(" /by ", 2)[1];
        if (time.equals("now")) {
            return LocalDate.now().format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } else {
            return LocalDate.parse(time).format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        }
    }

    private static boolean isValidEFormat(String body) {
        return body.split(" /at ", 2).length == 2;
    }

    private static String eventTime(String body) {
        String time = body.split(" /at ", 2)[1];
        if (time.equals("now")) {
            return LocalDate.now().format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        } else {
            return LocalDate.parse(time).format(DateTimeFormatter.ofPattern("d MMM yyyy"));
        }
    }

    public static boolean isBye(String input){
        return input.split(" ", 2)[0].equals("bye");
    }
}