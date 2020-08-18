import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String horizontalLine = "    ____________________________________________________________";
    private static final String indentation = "     ";
    private static final ArrayList<String> listOfCommands = new ArrayList<>();

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

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TaskManager taskManager = new TaskManager();
        greet();
        String command = scanner.nextLine();
        while(command != null) {
            if (command.equals("bye")) {
                exit();
                scanner.close();
                break;
            } else {
                if (command.equals("list")) {
                    System.out.println(horizontalLine);
                    taskManager.printList();
                    System.out.println(horizontalLine);
                } else if (command.contains("done")){
                    System.out.println(horizontalLine);
                    int index = command.length() - 1;
                    int taskNumber = Integer.parseInt(command.substring(index));
                    taskManager.markTaskAsDone(taskNumber);
                    System.out.println(horizontalLine);
                } else {
                    System.out.println(horizontalLine);
                    Task task = new Task(command);
                    taskManager.addTask(task);
                    System.out.println(horizontalLine);
                }
            }
            command = scanner.nextLine();
        }

    }
}
