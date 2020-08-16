import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) throws DukeException{
        printLogo();
        startGreet();
        startChat();
    }
    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    public static void startGreet() {
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
            } else if (input.startsWith("done")) {
                String[] stringArr;
                try {
                    // Checking if only one task is given to complete/valid int
                    stringArr = input.split(" ");
                    if (stringArr.length != 2) {
                        // Checking if only one task is given to complete
                        throw new DukeException("Oops, pls only enter one task to complete");
                    }
                    // Finding the actual task
                    int indexOfDone =  Integer.parseInt(stringArr[1]) - 1;
                    Task currentTask = list.get(indexOfDone);
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
                    throw new DukeException("Oops, pls enter a valid task number");
                }
            } else if (input.startsWith("todo")) {
                // Create and store todos given in list
                Task newTodo = handler.processTask(input, "todo");
                list.add(newTodo);
                printAddSuccess(list.size(), newTodo);
            } else if (input.startsWith("deadline")) {
                // Create and store deadlines given in list
                try {
                    Task newDeadline = handler.processTask(input, "deadline");
                    list.add(newDeadline);
                    printAddSuccess(list.size(), newDeadline);
                } catch (Exception e) {
                    throw new DukeException("Oops, correct add deadline format: deadline [task] /by [time]");
                }
            } else if (input.startsWith("event")) {
                // Create and store events given in list
                try {
                    Task newEvent = handler.processTask(input, "event");
                    list.add(newEvent);
                    printAddSuccess(list.size(), newEvent);
                } catch (Exception e) {
                    throw new DukeException("Oops, correct add event format: event [task] /at [time]");
                }
            } else {
                // Other command
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

    public static void printAddSuccess(int listSize, Task task) {
        // Prints success message after an item is added to list
        indent(1);
        System.out.print("Successfully added:\n");
        indent(2);
        System.out.println(task);
        indent(1);
        System.out.println("You have " + listSize + " task(s) in the list");
    }
}
