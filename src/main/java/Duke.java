import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public final Scanner scanner;
    public TaskManager taskManager;
    static String completeTaskPattern = "^done (?<taskNumber>[0-9]+)$";
    static String addEventPattern = "^(?<content>.+) /at (?<datetime>.+)$";
    static String addDeadlinePattern = "^(?<content>.+) /by (?<datetimeDue>.+)$";

    public Duke() {
        this.scanner = new Scanner(System.in);
        this.taskManager = new TaskManager();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        duke.sendMessage(duke.getGreetMessage());

        while (true) {
            String input = duke.getInput();
            if (input.equalsIgnoreCase("bye")) {
                duke.sendMessage(duke.getByeMessage());
                break;
            } else if (input.matches(Duke.completeTaskPattern)) {
                completeTask(duke, input);
            } else if (input.matches(Duke.addEventPattern)) {
                addEvent(duke, input);
            } else if (input.matches(Duke.addDeadlinePattern)) {
                addDeadline(duke, input);
            } else {
                addTodo(duke, input);
            }
        }
    }

    private static void addDeadline(Duke duke, String input) {
        Pattern r = Pattern.compile(Duke.addDeadlinePattern);
        Matcher m = r.matcher(input);
        m.find();
        String content = m.group("content");
        String datetimeDue = m.group("datetimeDue");
        duke.taskManager.addDeadline(content, datetimeDue);
        duke.sendMessage(duke.taskManager.toString());
    }

    private static void addEvent(Duke duke, String input) {
        Pattern r = Pattern.compile(Duke.addEventPattern);
        Matcher m = r.matcher(input);
        m.find();
        String content = m.group("content");
        String datetime = m.group("datetime");
        duke.taskManager.addEvent(content, datetime);
        duke.sendMessage(duke.taskManager.toString());
    }

    private static void addTodo(Duke duke, String input) {
        duke.taskManager.addTodo(input);
        duke.sendMessage(duke.taskManager.toString());
    }

    private static void completeTask(Duke duke, String input) {
        Pattern r = Pattern.compile(Duke.completeTaskPattern);
        Matcher m = r.matcher(input);
        m.find();
        int taskNumber = Integer.parseInt(m.group("taskNumber"));
        duke.taskManager.completeTask(taskNumber);
        duke.sendMessage(duke.taskManager.toString());
    }

    public String getGreetMessage() {
        return "Hello. What can I do for you?";
    }

    public String getByeMessage() {
        return "Bye. Hope to see you again soon!";
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
