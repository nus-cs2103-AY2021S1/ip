import java.io.IOException;
import java.util.ArrayList;

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
    }

    private static boolean isValidSize(String body, TaskList tasks) {
        String num = body.split(" ", 2)[0];
        int number = Integer.parseInt(num);
        return tasks.size() >= number && number > 0;
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
        return body.split(" /by ", 2)[1];
    }

    private static boolean isValidEFormat(String body) {
        return body.split(" /at ", 2).length == 2;
    }

    private static String eventTime(String body) {
        return body.split(" /at ", 2)[1];
    }

    public static boolean isBye(String input){
        return input.split(" ", 2)[0].equals("bye");
    }
}