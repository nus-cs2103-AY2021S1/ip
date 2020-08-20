import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a personal assistant chatbot to help a person keep track of various things.
 */
public class Duke {

    // Initialize an empty list of tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        // Print Duke's introduction
        String divider = "____________________________________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String welcome = "Hello! I'm Duke\nWhat can I do for you?\n";
        System.out.println(divider + logo + welcome + divider);

        // Read inputs from input.txt
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String nextInput = sc.nextLine();
            processInput(nextInput);

            // Exit the program if user says bye
            if (nextInput.equals("bye")) {
                break;
            }
        }
        sc.close();
    }

    /**
     * Processes the input fed to Duke.
     *
     * @param input Input string to be processed.
     */
    private static void processInput(String input) {
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (input.equals("list")) {
            printList();
        } else if (input.contains("done")) {
            int number = Integer.parseInt(input.substring(5));
            Task task = tasks.get(number - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this as done:");
            System.out.println(task.toString() + "\n");
        } else if (input.contains("todo") || input.contains("event") || input.contains("deadline")) {
            createTask(input);
        } else {
            // Other unrecognized commands
        }
    }

    /**
     * Prints the list of tasks stored in Duke.
     */
    private static void printList() {
        System.out.println("Here are the task(s) in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            int number = i + 1;
            System.out.println(number + "." + tasks.get(i).toString());
        }
        System.out.println();
    }

    /***
     * Creates a task.
     * Either a todo, an event or a deadline.
     *
     * @param input Input string describing a task.
     */
    private static void createTask(String input) {
        Task task;
        String taskString;

        if (input.contains("todo")) {   // todo
            taskString = input.substring(5);
            task = new Todo(taskString);
        } else if (input.contains("event")) {   // event
            taskString = input.substring(6);
            String[] arr = taskString.split(" /at ", 2);
            task = new Event(arr[0], arr[1]);
        } else {    // deadline
            taskString = input.substring(9);
            String[] arr = taskString.split(" /by ", 2);
            task = new Deadline(arr[0], arr[1]);
        }

        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        int numOfTasks = tasks.size();
        if (numOfTasks == 1) {
            System.out.println("Now you have " + numOfTasks + " task in the list.\n");
        } else {
            System.out.println("Now you have " + numOfTasks + " tasks in the list.\n");
        }
    }
}
