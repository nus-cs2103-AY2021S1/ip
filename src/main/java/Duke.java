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
                System.out.println();
                indent(1);
                System.out.println("Bye bye! Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                // Prints the given list
                handler.printList();
            } else if (input.startsWith("done ")) {
                try {
                    Task currentTask = handler.findTaskByNum(input, list);
                    if (currentTask.isDone) {
                        System.out.println("Task: " + currentTask.description + " has already been completed!");
                        continue;
                    }
                    // Mark given task as done
                    currentTask.markAsDone();
                    indent(1);
                    System.out.println("Good job! You completed:");
                    indent(2);
                    System.out.println(currentTask);
                } catch (Exception e) {
                    throw new DukeException("Oops, pls enter a valid task to complete");
                }
            } else if (input.startsWith("delete ")){
                Task currentTask = handler.findTaskByNum(input, list);
                try {
                    list.remove(currentTask);
                    printSuccess(list.size(),currentTask, "del");
                } catch (Exception e) {
                    throw new DukeException("Oops, pls enter a valid task to delete");
                }
            } else if (input.startsWith("todo ")) {
                // Create and store todos given in list
                Task newTodo = handler.processTask(input, "todo");
                list.add(newTodo);
                printSuccess(list.size(), newTodo, "add");
            } else if (input.startsWith("deadline")) {
                // Create and store deadlines given in list
                try {
                    Task newDeadline = handler.processTask(input, "deadline");
                    list.add(newDeadline);
                    printSuccess(list.size(), newDeadline, "add");
                } catch (Exception e) {
                    throw new DukeException("Oops, use add deadline format: deadline [task] /by [time]");
                }
            } else if (input.startsWith("event")) {
                // Create and store events given in list
                try {
                    Task newEvent = handler.processTask(input, "event");
                    list.add(newEvent);
                    printSuccess(list.size(), newEvent, "add");
                } catch (Exception e) {
                    throw new DukeException("Oops, use add event format: event [task] /at [time]");
                }
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

    public static void printSuccess(int listSize, Task task, String operation) {
        // Prints success message and list size after task added/deleted
        indent(1);
        if (operation.equals("add")) {
            System.out.print("Successfully added:\n");
        } else {
            System.out.print("Noted. I've removed this task:\n");
        }
        indent(2);
        System.out.println(task);
        indent(1);
        System.out.println("You have " + listSize + " task(s) in the list.");
    }
}
