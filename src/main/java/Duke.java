import java.util.Scanner;
import java.util.List;

public class Duke {
    public static void greet() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tHello! I'm Duke");
        System.out.println("\tWhat can I do for you?");
        System.out.println("\t____________________________________________________________\n");
    }

    public static void farewell() {
        System.out.println("\t____________________________________________________________");
        System.out.println("\tBye. Hope to see you again soon!");
        System.out.println("\t____________________________________________________________");
    }

    public static void main(String[] args) throws IndexOutOfBoundsException {
        Duke.greet();
        Scanner sc = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        String userCommand;

        while (sc.hasNext()) {
            userCommand = sc.nextLine();
            if (userCommand.equals("bye")) {
                break;
            } else if (userCommand.equals("list")) { // Show list of tasks
                taskManager.showAllTask();
            } else if (userCommand.contains("done")) {
                taskManager.markTaskDone(userCommand);
            } else if (userCommand.contains("todo") || userCommand.contains("deadline") || userCommand.contains("event")) { // Add Task
                taskManager.addTask(userCommand);
            } else {
                // If a task is specified as a Task but not a Deadline / To Do / Event, throw an error
                DukeException.genericTask();
            }
        }
        Duke.farewell();
    }
}
