import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private final String line = "--------------------------------------------------";
    private final String stringFormat = line + "\n%s\n" + line + "\n\n";

    private Scanner scanner;
    private List<Task> list;

    private Duke() {
        this.scanner = new Scanner(System.in);
        this.list = new ArrayList<>();
        greet();
    }

    private void greet() {
        System.out.printf(stringFormat, "Hello! I'm Duke\nWhat can I do for you?");
    }

    private void processBye() {
        System.out.printf(stringFormat, "Bye. Hope to see you again soon!");
    }

    private void processAdd(Task task) {
        this.list.add(task);
        String content = String.format("Got it. I've added this task:\n" +
                        "\t%1$s\n" +
                        "Now you have %2$d tasks in the list.",
                        task.toString(), this.list.size());
        System.out.printf(stringFormat, content);
    }

    private void processList() {
        if (this.list.size() < 1) {
            System.out.printf(stringFormat, "There is nothing in the list!!");
            return;
        }
        StringBuilder content = new StringBuilder();
        for (int i = 0; i < this.list.size(); i++) {
            content.append(i + 1);
            content.append(". " + this.list.get(i).toString());
            if (i < this.list.size() - 1) {
                content.append("\n");
            }
        }
        System.out.printf(stringFormat, content.toString());
    }

    private void processDone(String action) {
        String[] splitKeys = action.split(" ");
        if (splitKeys.length < 2) {
            System.out.printf(stringFormat, "Please key in a valid task number after 'done'");
            return;
        }

        int taskIndex = Integer.parseInt(splitKeys[1]);
        if (taskIndex > this.list.size() || taskIndex < 1) {
            System.out.printf(stringFormat, "Please key in a valid task number!");
            return;
        }
        Task task = this.list.get(taskIndex - 1);
        task.markAsDone();
        System.out.printf(stringFormat, "Nice! I've marked this task as done:\n\t" + task.toString());
    }

    private void createTodo(String action) {
        String description = new StringBuilder(action).substring(5);
        System.out.println(description);
        Todo newTodo = new Todo(description);
        this.processAdd(newTodo);
    }

    private void createDeadline(String action) {
        if (!action.contains(" /by ")) {
            System.out.printf(stringFormat, "Invalid format, missing '/by' key!!");
            return;
        }
        String[] taskDetails = new StringBuilder(action).substring(9).split(" /by ");
        Deadline newDeadline = new Deadline(taskDetails[0], taskDetails[1]);
        this.processAdd(newDeadline);
    }

    private void createEvent(String action) {
        if (!action.contains(" /at ")) {
            System.out.printf(stringFormat, "Invalid format, missing '/at' key!!");
            return;
        }
        String[] taskDetails = new StringBuilder(action).substring(6).split(" /at ");
        Event newEvent = new Event(taskDetails[0], taskDetails[1]);
        this.processAdd(newEvent);
    }

    private boolean processAction() {
        System.out.println("Type an action (list, done, todo, deadline, event, bye):");
        String action = this.scanner.nextLine();
        String key = action.split(" ")[0];
        switch (key) {
            case "bye":
                this.processBye();
                return false;
            case "list":
                this.processList();
                break;
            case "done":
                this.processDone(action);
                break;
            case "todo":
                this.createTodo(action);
                break;
            case "deadline":
                this.createDeadline(action);
                break;
            case "event":
                this.createEvent(action);
                break;
            default:
                System.out.printf(stringFormat, "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                break;
        }
        return true;
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        boolean status;
        do {
            status = duke.processAction();
        } while (status);

    }
}
