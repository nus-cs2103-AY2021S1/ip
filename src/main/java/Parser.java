import java.util.List;
import java.util.Scanner;

public class Parser {
    public static void run() {
        Scanner sc = new Scanner(System.in);
        String userCommand;

        // Load duke.txt into task list
        List<Task> tasks = Storage.loadTextIntoTaskList();
        TaskList tasklist = new TaskList(tasks);

        while (sc.hasNext()) {
            userCommand = sc.nextLine();
            if (userCommand.equals("bye")) {
                break;
            } else if (userCommand.equals("list")) { // Show list of tasks
                TaskDescription.showAllTask(tasklist.getTasks());
            } else if (userCommand.contains("done")) { // e.g done 1
                tasklist.markTaskDone(userCommand);
            } else if (userCommand.contains("todo") || userCommand.contains("deadline") || userCommand.contains("event")) { // Add Task, e.g todo work
                tasklist.addTask(userCommand);
            } else if (userCommand.contains("delete")) {
                tasklist.deleteTask(userCommand);
            } else {
                // If a task is specified as a Task but not a Deadline / To Do / Event, throw an error
                DukeException.genericTask();
            }
        }
    }
}
