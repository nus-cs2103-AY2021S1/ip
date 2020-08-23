import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public final Scanner scanner;
    public TaskManager taskManager;
    static String deleteTaskPattern = "^delete (?<taskNumber>[0-9]+)$";
    static String completeTaskPattern = "^done (?<taskNumber>[0-9]+)$";
    static String addEventPattern = "^event (?<content>.+) /at (?<datetime>.+)$";
    static String addDeadlinePattern = "^deadline (?<content>.+) /by (?<datetimeDue>.+)$";
    static String addTodoPattern = "^todo (?<content>.+)$";
    static String listPattern = "^list$";
    static String byePattern = "^bye$";
    static String greetMessage = "Hello. What can I do for you?";
    static String byeMessage = "Bye. Hope to see you again soon!";
    static String listStringFormat = "Here are the tasks in your list:\n" +
            "%sYou have %s task(s) in your list.";
    static String successfulTaskAddStringFormat = "Got it. I've added this task:\n" +
            "%s\n" +
            "Now you have %d task(s) in the list.";
    static String successfulTaskCompleteStringFormat = "Nice! I've marked this task as done:\n" +
            "%s";
    static String successfulTaskDeleteStringFormat = "Noted. I've removed this task:\n" +
            "%s\n" +
            "Now you have %d task(s) in the list.";

    public Duke() {
        this.scanner = new Scanner(System.in);
        try {
            this.taskManager = new TaskManager();
        } catch (DukeException | IOException exception) {
            System.out.println(exception.getMessage());
            this.taskManager = null;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
        if (duke.taskManager == null) {
            return;
        }
        duke.sendMessage(duke.getGreetMessage());

        while (true) {
            String input = duke.getInput();
            if (input.matches(Duke.byePattern)) {
                duke.sendMessage(duke.getByeMessage());
                break;
            } else if (input.matches(Duke.completeTaskPattern)) {
                completeTask(duke, input);
            } else if (input.matches(Duke.deleteTaskPattern)) {
                deleteTask(duke, input);
            } else if (input.matches(Duke.addEventPattern)) {
                addEvent(duke, input);
            } else if (input.matches(Duke.addDeadlinePattern)) {
                addDeadline(duke, input);
            } else if (input.matches(Duke.addTodoPattern)) {
                addTodo(duke, input);
            } else if (input.matches(Duke.listPattern)) {
                listTasks(duke);
            } else {
                duke.sendMessage("I don't know what you just sent...");
            }
        }
    }

    private static void listTasks(Duke duke) {
        String message = String.format(
                listStringFormat,
                duke.taskManager.toString(),
                duke.taskManager.getTaskCount());
        duke.sendMessage(message);
    }

    private static void addDeadline(Duke duke, String input) {
        Pattern r = Pattern.compile(Duke.addDeadlinePattern);
        Matcher m = r.matcher(input);
        m.find();
        String content = m.group("content");
        String datetimeDue = m.group("datetimeDue");
        try {
            Deadline deadline = duke.taskManager.addDeadline(content, datetimeDue);
            duke.sendMessage(duke.getAddSuccessMessage(deadline));
        } catch (DukeException | IOException exception) {
            duke.sendMessage(exception.getMessage());
        }
    }



    private static void addEvent(Duke duke, String input) {
        Pattern r = Pattern.compile(Duke.addEventPattern);
        Matcher m = r.matcher(input);
        m.find();
        String content = m.group("content");
        String datetime = m.group("datetime");
        try {
            Event event = duke.taskManager.addEvent(content, datetime);
            duke.sendMessage(duke.getAddSuccessMessage(event));
        } catch (DukeException | IOException exception) {
            duke.sendMessage(exception.getMessage());
        }
    }

    private static void addTodo(Duke duke, String input) {
        Pattern r = Pattern.compile(Duke.addTodoPattern);
        Matcher m = r.matcher(input);
        m.find();
        String content = m.group("content");
        try {
            Todo todo = duke.taskManager.addTodo(content);
            duke.sendMessage(duke.getAddSuccessMessage(todo));
        } catch (DukeException | IOException exception) {
            duke.sendMessage(exception.getMessage());
        }
    }

    private static void completeTask(Duke duke, String input) {
        Pattern r = Pattern.compile(Duke.completeTaskPattern);
        Matcher m = r.matcher(input);
        m.find();
        int taskNumber = Integer.parseInt(m.group("taskNumber"));
        try {
            Task task = duke.taskManager.completeTask(taskNumber);
            duke.sendMessage(duke.getCompleteSuccessMessage(task));
        } catch (DukeException | IOException exception) {
            duke.sendMessage(exception.getMessage());
        }
    }

    private static void deleteTask(Duke duke, String input) {
        Pattern r = Pattern.compile(Duke.deleteTaskPattern);
        Matcher m = r.matcher(input);
        m.find();
        int taskNumber = Integer.parseInt(m.group("taskNumber"));
        try {
            Task task = duke.taskManager.deleteTask(taskNumber);
            duke.sendMessage(duke.getDeleteSuccessMessage(task));
        } catch (DukeException | IOException exception) {
            duke.sendMessage(exception.getMessage());
        }
    }

    private String getDeleteSuccessMessage(Task task) {
        return String.format(
                successfulTaskDeleteStringFormat,
                task,
                this.taskManager.getTaskCount());
    }

    public String getGreetMessage() {
        return greetMessage;
    }

    public String getByeMessage() {
        return byeMessage;
    }

    private String getCompleteSuccessMessage(Task task) {
        return String.format(
                successfulTaskCompleteStringFormat,
                task);
    }

    private String getAddSuccessMessage(Task task) {
        return String.format(
                successfulTaskAddStringFormat,
                task,
                this.taskManager.getTaskCount());
    }

    public void sendMessage(String message) {
        System.out.println(String.format("----------\n%s\n----------\n", message));
        return;
    }

    public String getInput() {
        String input = this.scanner.nextLine();
        return input;
    }
}
