import java.io.EOFException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args)  {
        initialize();
        startChat();
    }
    public static void initialize() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        String greeting = "Hello! I'm Duke the chatbot! \n" +
                "What can I do for you?\n";
        System.out.println(greeting);
    }

    public static void startChat() {
        Scanner scanner = new Scanner(System.in);
        TaskHandler handler = new TaskHandler();
        ArrayList<Task> list = handler.getTaskList();
        while (true) {
            // Listens for input
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                // Encounters exit command
                indent(1);
                System.out.println("Bye bye! Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                // Prints the given list
                try {
                    handler.printList();
                } catch (DukeException e) {
                    e.printStackTrace(System.out);
                }
            } else if (input.startsWith("done ") || input.equals("done")) {
                 try {
                     Task currentTask = handler.modifyTask(input, list, TaskHandler.operationType.DONE);
                     printSuccess("done", currentTask, list.size());
                 } catch (DukeException e) {
                     e.printStackTrace(System.out);
                 }
            } else if (input.startsWith("delete ") || input.equals("delete")){
                try {
                    Task delTask = handler.modifyTask(input, list, TaskHandler.operationType.DELETE);
                    printSuccess("delete", delTask, list.size());
                } catch (DukeException e) {
                    e.printStackTrace(System.out);
                }
            } else if (input.startsWith("todo ") || input.equals("todo")) {
                // Create and store todos given in list
                try {
                    Task newTodo = handler.processNewTask(input, Task.taskType.TODO);
                    list.add(newTodo);
                    printSuccess("add", newTodo, list.size());
                } catch (DukeException e){
                    e.printStackTrace(System.out);
                }
            } else if (input.startsWith("deadline ") || input.equals("deadline")) {
                // Create and store deadlines given in list
                try {
                    Task newDeadline = handler.processNewTask(input, Task.taskType.DEADLINE);
                    list.add(newDeadline);
                    printSuccess("add", newDeadline, list.size());
                }  catch (DukeException e){
                    e.printStackTrace(System.out);
                }

            } else if (input.startsWith("event ") || input.equals("event")) {
                // Create and store events given in list
                try {
                    Task newEvent = handler.processNewTask(input, Task.taskType.EVENT);
                    list.add(newEvent);
                    printSuccess("add", newEvent, list.size());
                } catch (DukeException e){
                    e.printStackTrace(System.out);
                }
            } else {
                // Other commands
                try {
                    handler.receiveInvalidCommand();
                } catch (DukeException e) {
                    e.printStackTrace(System.out);
                }
            }
            System.out.println();
        }
    }

    public static void indent(int times) {
        for (int i=0; i<times; i++) {
            System.out.print("    ");
        }
    }

    public static void printSuccess(String operation, Task currentTask, int listSize) {
        // Prints success message and list size after task added/deleted
        indent(1);
        if (operation.equals("add")) {
            System.out.print("Successfully added:\n");
        } else if (operation.equals("delete")) {
            System.out.print("Noted. I've removed this task:\n");
        } else {
            System.out.println("Good job! You completed:");
            indent(2);
            System.out.println(currentTask);
            return;
        }
        indent(2);
        System.out.println(currentTask);
        indent(1);
        System.out.println("You have " + listSize + " task(s) in the list.");
    }
}
