import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException{
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

    public static void startChat() throws DukeException {
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
                handler.printList();
            } else if (input.startsWith("done ")) {
                Task doneTask = handler.doTask(input, list);
                printSuccess("done", doneTask, list.size());
            } else if (input.startsWith("delete ")){
                Task delTask = handler.deleteTask(input, list);
                printSuccess("delete", delTask, list.size());
            } else if (input.startsWith("todo ")) {
                // Create and store todos given in list
                Task newTodo = handler.sortTask(input, "todo");
                list.add(newTodo);
                printSuccess("add", newTodo, list.size());
            } else if (input.startsWith("deadline ")) {
                // Create and store deadlines given in list
                Task newDeadline = handler.sortTask(input, "deadline");
                list.add(newDeadline);
                printSuccess("add", newDeadline, list.size());
            } else if (input.startsWith("event ")) {
                // Create and store events given in list
                Task newEvent = handler.sortTask(input, "event");
                list.add(newEvent);
                printSuccess("add", newEvent, list.size());
            } else {
                // Other commands
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
            System.out.println();
        }
    }

    public static void indent(int times) {
        for (int i=0; i<times; i++) {
            System.out.print("    ");
        }
    }

    public static void printSuccess(String operation, Task task, int listSize) {
        // Prints success message and list size after task added/deleted
        indent(1);
        if (operation.equals("add")) {
            System.out.print("Successfully added:\n");
        } else if (operation.equals("delete")){
            System.out.print("Noted. I've removed this task:\n");
        } else {
            System.out.println("Good job! You completed:");
            indent(2);
            System.out.println(task);
            return;
        }
        indent(2);
        System.out.println(task);
        indent(1);
        System.out.println("You have " + listSize + " task(s) in the list.");
    }
}
