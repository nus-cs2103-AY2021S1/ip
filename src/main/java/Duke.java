import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String dash = String.valueOf('\u2500').repeat(5);
    private static final String greeting = "Hello! I'm Duke \n" +
            "What can I do for you?";
    private static final String task_read = "Here are the tasks in your list: \n";
    private static final String task_completed = "Nice! I've marked this task as complete. \n";
    private static final String farewell = "Bye. Hope to see you again soon.";
    private boolean running = true;
    private List<Task> taskList = new ArrayList<>(100);

    public Duke() {
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        Duke servingDuke = new Duke();
        System.out.println(servingDuke.constructMessage(greeting));
        while (servingDuke.canRun()) {
            String input = sc.nextLine();
            servingDuke.respondToInput(input);
        }
    }

    private void respondToInput(String rawInput) {
        String input = rawInput.trim();
        String[] breakdown = input.split(" ", 2);
        if (input.equals("bye")) {
            running = false;
            System.out.println(
                    constructMessage(farewell));
        } else if (input.equals("list")) {
            System.out.println(
                    constructMessage(task_read + printAsString(taskList)));
        } else if (!input.isEmpty()) {
            Task newTask = new Task(input);
            taskList.add(newTask);
            System.out.println(constructMessage("added: " + input));
        }
    }

    private String printAsString(List<Task> taskList) {
        StringBuilder res = new StringBuilder();
        for (int i = 1; i <= taskList.size(); i++) {
            res.append(i)
                    .append(". ")
                    .append(taskList.get(i - 1));
            if (i != taskList.size()) {
                res.append("\n");
            }
        }
        return res.toString();
    }

    private boolean canRun() {
        return running;
    }

    public String constructMessage(String message) {
        return dash + "\n" + message + "\n" + dash;
    }
}
