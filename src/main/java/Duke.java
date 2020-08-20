import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Duke {

    private static List<Task> tasks = new ArrayList<>();

    public static void greet() {
        System.out.println("Hello! I'm Duke.");
        System.out.println("What can I do for you?\n");
    }

    public static void handleInput() {
        boolean exit = false;
        Scanner sc = new Scanner(System.in);
        String command;
        while (!exit) {
            try {
                command = sc.nextLine();
                String errorMessage = "";
                try {
                    if (command.equals("bye")) { // Exits while loop
                        exit = true;
                        sc.close();
                    } else if (command.equals("list")) {
                        displayTasks();
                    } else if (command.startsWith("done")) {
                        int position = Integer.parseInt(command.substring(5));
                        markTaskAsDone(position);
                    } else if (command.startsWith("todo")) {
                        String description = command.substring(5);
                        Task task = new Todo(description);
                        addTask(task);
                    } else if (command.startsWith("deadline")) {
                        String[] deadline = command.substring(9).split("/by");
                        String description = deadline[0].trim();
                        String by = deadline[1].trim();
                        Task task = new Deadline(description, by);
                        addTask(task);
                    } else if (command.startsWith("event")) {
                        String[] event = command.substring(6).split("/at");
                        String description = event[0].trim();
                        String at = event[1].trim();
                        Task task = new Event(description, at);
                        addTask(task);
                    } else {
                        errorMessage = "Sorry! I don't know what that means...";
                        throw new DukeException(errorMessage);
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    if (isTask(command)) {
                        errorMessage = "OOPS!!! Description of a task cannot be empty :(";
                    } else if (command.startsWith("done")) {
                        errorMessage = "Missing task number! Please ensure to key in the task number :)";
                    }
                    throw new DukeException(errorMessage);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static boolean isTask(String command) {
        return command.startsWith("todo") 
            || command.startsWith("deadline")
            || command.startsWith("event");
    }

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Okay! Task added for you!");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " task(s) in the list." + "\n");
    }

    public static void displayTasks() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + ". " + tasks.get(i));
        }
        System.out.println("");
    }

    public static void markTaskAsDone(int position) {
        Task task = tasks.get(position - 1);
        task.markAsDone();
        System.out.println("Good job! I've marked this task as done:");
        System.out.println(task + "\n");
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        greet();
        handleInput();
        exit();
    }
}
