import java.util.Scanner;

public class Duke {
    private static Task[] tasks = new Task[100];
    private static int numberOfTasks = 0;

    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        printResponse("Hello! I'm Duke\nWhat can I do for you?");
    }

    private static void printResponse(String response) {
        System.out.println("____________________________________________________________");
        System.out.println(response);
        System.out.println("____________________________________________________________");
    }

    private static void printAddTask(Task task) {
        printResponse("Got it. I've added this task: \n\t" + task + "\nNow you have " + numberOfTasks + " tasks in the list.");
    }

    private static void handleUserInputs() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String userInput = sc.nextLine().trim();
            String[] splitInput = userInput.split(" ", 2);
            try {
                if (splitInput[0].equals("list")) {
                    String numberedList = "Here are the tasks in your list:";
                    for (int i = 0; i < numberOfTasks; i++) {
                        numberedList += "\n" + (i + 1) + "." + tasks[i];
                    }
                    printResponse(numberedList);
                } else if (splitInput[0].equals("bye")) {
                    printResponse("Bye. Hope to see you again soon!");
                    return;
                } else if (splitInput[0].equals("done")) {
                    try {
                        int taskNumber = Integer.parseInt(splitInput[1]);
                        tasks[taskNumber - 1].markAsDone();
                        printResponse("Nice! I've marked this task as done:\n" + tasks[taskNumber - 1]);
                    } catch (NullPointerException | NumberFormatException | ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException("☹ OOPS!!! The task number is not valid.");
                    }
                } else if (splitInput[0].equals("todo")) {
                    try {
                        tasks[numberOfTasks] = new Todo(splitInput[1]);
                    } catch(ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    printAddTask(tasks[numberOfTasks++]);
                } else if (splitInput[0].equals("deadline")) {
                    try {
                        String[] splitDescription = splitInput[1].split(" /by ", 2);
                        tasks[numberOfTasks] = new Deadline(splitDescription[0], splitDescription[1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException("☹ OOPS!!! The description or date of a deadline cannot be empty.");
                    }
                    printAddTask(tasks[numberOfTasks++]);
                } else if (splitInput[0].equals("event")) {
                    try {
                        String[] splitDescription = splitInput[1].split(" /at ", 2);
                        tasks[numberOfTasks] = new Event(splitDescription[0], splitDescription[1]);
                    } catch (ArrayIndexOutOfBoundsException ex) {
                        throw new DukeException("☹ OOPS!!! The description or date of an event cannot be empty.");
                    }
                    printAddTask(tasks[numberOfTasks++]);
                } else {
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch(DukeException ex) {
                System.err.println(ex.getMessage());
            }

        }
    }

    public static void main(String[] args) {
        greet();
        handleUserInputs();
    }
}
