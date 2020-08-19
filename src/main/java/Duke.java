import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String dash = String.valueOf('\u2500').repeat(5);
    private static final String greeting = "Hello! I'm Duke \n" +
            "What can I do for you?";
    private static final String task_read = "Here are the tasks in your list: \n";
    private static final String task_completed = "Nice! I've marked this task as complete. \n";
    private static final String task_index_out_of_bounds = "That task does not exist.";
    private static final String farewell = "Bye. Hope to see you again soon.";
    private static final String task_number_format = "Invalid. Please try with the following format: done[space][task number in numerals]";
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
        //Remove leading and trailing whitespaces
        String input = rawInput.trim();
        String[] breakdown = input.split(" ", 2);
        if (!input.isEmpty()) {
            if (input.equals("bye")) {
                running = false;
                System.out.println(
                        constructMessage(farewell));
            } else if (input.equals("list")) {
                System.out.println(
                        constructMessage(task_read + printAsString(taskList)));
            } else if (breakdown[0].equals("done")) {
                markTaskAsDone(breakdown);
            } else {
                Task newTask = new Task(input);
                taskList.add(newTask);
                System.out.println(constructMessage("added: " + input));
            }
        }
    }

    private void markTaskAsDone(String[] breakdown) {
        try {
            int taskNumber = Integer.parseInt(breakdown[1]);
            Task completedTask = taskList.get(taskNumber - 1);
            completedTask.markDone();
            System.out.println(constructMessage(
                    task_completed + completedTask.toString()
            ));
        } catch (IndexOutOfBoundsException e) {
            System.out.println(constructMessage(task_index_out_of_bounds));
        } catch (NumberFormatException e) {
            System.out.println(constructMessage(task_number_format));
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
