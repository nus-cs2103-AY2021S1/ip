import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void printTaskList(List<Task> taskList) {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks!");
        } else {
            int taskNumber = 1;
            while (taskNumber < taskList.size() + 1) {
                Task task = taskList.get(taskNumber - 1);
                System.out.println(taskNumber + "." + task.toString());
                taskNumber++;
            }
        }
    }

    public static void markTaskAsDone(List<Task> taskList, int taskNumber) {
        Task task = taskList.get(taskNumber - 1);
        task.markAsDone();
        taskList.set(taskNumber - 1, task);
        System.out.println("Nice! I've marked this task as done:\n" + "  " + task.toString());
    }

    public static void main(String[] args) {
        // Print introduction
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        // Initialize empty task list
        List<Task> taskList = new ArrayList<>();

        // Receive commands
        Scanner scanner = new Scanner(System.in);
        String command;

        while (true) {
            command = scanner.nextLine();
            String firstWordOfCommand = command.split(" ", 2)[0];

            if (command.equals("list")) {
                printTaskList(taskList);

            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;

            // Mark a task as done
            } else if (firstWordOfCommand.equals("done")) {
                int taskNumber = Integer.parseInt(command.split(" ", 2)[1]);
                markTaskAsDone(taskList, taskNumber);

            // Add task to task list
            } else {
                Task task;
                String remainingWordsOfCommand = command.split(" ", 2)[1];
                if (firstWordOfCommand.equals("todo")) {
                    task = new Todo(remainingWordsOfCommand);
                } else if (firstWordOfCommand.equals("deadline")) {
                    String description = remainingWordsOfCommand.split(" /by ")[0];
                    String by = remainingWordsOfCommand.split(" /by ")[1];
                    task = new Deadline(description, by);
                } else { // Event
                    String description = remainingWordsOfCommand.split(" /at ")[0];
                    String at = remainingWordsOfCommand.split(" /at ")[1];
                    task = new Event(description, at);
                }
                taskList.add(task);
                System.out.println("Got it. I've added this task:\n"
                        + "  " + task.toString() + "\n"
                        + "Now you have " + taskList.size() + " tasks in the list");
            }
        }
    }
}
