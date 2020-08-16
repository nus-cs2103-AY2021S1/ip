import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static void printTaskList(List<String> taskList) {
        if (taskList.size() == 0) {
            System.out.println("You have no tasks!");
        } else {
            int taskNumber = 1;
            while (taskNumber < taskList.size() + 1) {
                System.out.println(taskNumber + ". " + taskList.get(taskNumber - 1));
                taskNumber++;
            }
        }
    }

    public static void main(String[] args) {
        // Print introduction
        System.out.println("Hello! I'm Duke\n" + "What can I do for you?\n");

        // Initialize empty task list
        List<String> taskList = new ArrayList<>();

        // Receive commands
        Scanner scanner = new Scanner(System.in);
        String command = "";

        while (true) {
            command = scanner.nextLine();
            if (command.equals("list")) {
                printTaskList(taskList);
            } else if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else {
                // Add task to task list
                taskList.add(command);
                System.out.println("added: " + command);
            }
        }
    }
}
