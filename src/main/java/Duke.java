
import java.util.Scanner;

public class Duke {
    private static final String horizontalLine = "    ____________________________________________________________";
    private static final String indentation = "     ";
    private static TaskManager taskManager;

    public static void greet() {
        System.out.println(horizontalLine);
        System.out.println(indentation + "Hello! I'm Duke");
        System.out.println(indentation + "What can I do for you?");
        System.out.println(horizontalLine);
    }

    public static void exit() {
        String bye = "Bye. Hope to see you again soon!";
        System.out.println(horizontalLine);
        System.out.println(indentation + bye);
        System.out.println(horizontalLine);
    }

    public static void commandProcessor(String command) throws DukeException {
        System.out.println(horizontalLine);
        if (command.equals("list")) {
            taskManager.printList();
        } else {
            String[] strArr = command.split(" ", 2);
            String taskType = strArr[0];
            if (taskType.equals("done")) {
                int index = command.length() - 1;
                int taskNumber = Integer.parseInt(strArr[1]);
                taskManager.markTaskAsDone(taskNumber);
            } else if (taskType.equals("delete")) {
                int index = command.length() - 1;
                int taskNumber = Integer.parseInt(strArr[1]);
                taskManager.deleteTask(taskNumber);
            } else if (taskType.equals("todo")) {
                if (strArr.length == 1) {
                    throw new DukeException(indentation + "☹ OOPS!!! The description of a todo cannot be empty.");
                } else {
                    Todo todo = new Todo(strArr[1]);
                    taskManager.addTask(todo);
                }
            } else if (taskType.equals("deadline")) {
                if (strArr.length == 1) {
                    throw new DukeException(indentation + "☹ OOPS!!! The description of a deadline cannot be empty.");
                } else {
                    String[] deadlineArr = strArr[1].split(" /by ", 2);
                    Deadline deadline = new Deadline(deadlineArr[0], deadlineArr[1]);
                    taskManager.addTask(deadline);
                }
            } else if (taskType.equals("event")) {
                if (strArr.length == 1) {
                    throw new DukeException(indentation + "☹ OOPS!!! The description of an event cannot be empty.");
                } else {
                    String[] eventArr = strArr[1].split(" /at ", 2);
                    Event event = new Event(eventArr[0], eventArr[1]);
                    taskManager.addTask(event);
                }
            } else {
                throw new DukeException(indentation + "☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        }
        System.out.println(horizontalLine);
    }


    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner scanner = new Scanner(System.in);
        taskManager = new TaskManager();
        greet();
        String command = scanner.nextLine();
        while (command != null) {
            try {
                if (command.equals("bye")) {
                    exit();
                    scanner.close();
                    break;
                } else {
                    commandProcessor(command);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                System.out.println(horizontalLine);
            }
            command = scanner.nextLine();
        }
    }
}
