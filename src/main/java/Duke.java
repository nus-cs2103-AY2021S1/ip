import java.util.ArrayList;
import java.util.Scanner;

/**
 * Represents a personal assistant chatbot to help a person keep track of various things.
 */
public class Duke {

    // Initialize an empty list of tasks
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        // Check if save file exists.
        DukeSaveFile.checkSaveFile();

        // Print Duke's introduction
        String divider = "____________________________________________________________\n";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n\n";
        String welcome = "Hello! I'm Duke.\n";
        System.out.println(divider + logo + welcome + divider);

        // Read inputs from input.txt
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String nextInput = sc.nextLine();
            try {
                processInput(nextInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

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
     * @throws DukeException If input is invalid.
     */
    private static void processInput(String input) throws DukeException {
        if (input.equals("bye")) {
            System.out.println("Bye. Hope to see you again soon!");
        } else if (input.equals("list")) {
            printList();
        } else if (input.contains("delete")) {
            deleteTask(input);
        } else if (input.contains("done")) {
            markTaskAsDone(input);
        } else if (input.contains("todo") || input.contains("event") || input.contains("deadline")) {
            createTask(input);
        } else {
            throw new DukeException("\u2639 OOPS!!! I'm sorry, but I don't know what that means :-(\n");
        }
    }

    /**
     * Prints the list of tasks stored in Duke.
     */
    private static void printList() {
        int numOfTasks = tasks.size();
        if (numOfTasks == 0) {
            System.out.println("There are no tasks in your list.\n");
        } else {
            System.out.println("Here are the task(s) in your list:");
            for (int i = 0; i < numOfTasks; i++) {
                int number = i + 1;
                System.out.println(number + "." + tasks.get(i).toString());
            }
            System.out.println();
        }
    }

    /**
     * Prints the number of tasks in the list.
     */
    private static void printNumOfTasks() {
        int numOfTasks = tasks.size();
        if (numOfTasks == 1) {
            System.out.println("Now you have " + numOfTasks + " task in the list.\n");
        } else {
            System.out.println("Now you have " + numOfTasks + " tasks in the list.\n");
        }
    }

    /**
     * Deletes a task from the list.
     *
     * @param input Input string describing which task's index to be deleted.
     * @throws DukeException If input is invalid.
     */
    private static void deleteTask(String input) throws DukeException {
        try {
            int number = Integer.parseInt(input.substring(7));
            int index = number - 1;
            Task task = tasks.get(index);
            tasks.remove(index);
            System.out.println("Noted. I've removed this task:");
            System.out.println(task.toString());
            printNumOfTasks();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\u2639 OOPS!!! Which task do you want to delete?\n");
        } catch (NumberFormatException e) {
            throw new DukeException("\u2639 OOPS!!! Enter the index of the task to be deleted.\n");
        }
    }

    /**
     * Marks a task as done.
     *
     * @param input Input string describing which task's index is done.
     * @throws DukeException If input is invalid.
     */
    private static void markTaskAsDone(String input) throws DukeException {
        try {
            int number = Integer.parseInt(input.substring(5));
            Task task = tasks.get(number - 1);
            task.markAsDone();
            System.out.println("Nice! I've marked this as done:");
            System.out.println(task.toString() + "\n");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("\u2639 OOPS!!! Which task have you done?\n");
        } catch (NumberFormatException e) {
            throw new DukeException("\u2639 OOPS!!! Enter the index of the task done.\n");
        }
    }

    /***
     * Creates a task.
     * Either a todo, an event or a deadline.
     *
     * @param input Input string describing a task.
     * @throws DukeException If inout is invalid.
     */
    private static void createTask(String input) throws DukeException {
        Task task;
        String taskString;

        try {
            if (input.contains("todo")) {   // todo
                taskString = input.substring(5);
                task = new Todo(taskString);
            } else if (input.contains("event")) {   // event
                taskString = input.substring(6);
                String[] arr = taskString.split(" /at ", 2);
                if (arr.length < 2 || arr[1].equals("")) {
                    throw new DukeException("\u2639 OOPS!!! Enter the date and/or time of the event after \"/at\".\n");
                }
                task = new Event(arr[0], arr[1]);
            } else {    // deadline
                taskString = input.substring(9);
                String[] arr = taskString.split(" /by ", 2);
                if (arr.length < 2 || arr[1].equals("")) {
                    throw new DukeException("\u2639 OOPS!!! Enter the date and/or time of the deadline after \"/by\".\n");
                }
                task = new Deadline(arr[0], arr[1]);
            }
        } catch (IndexOutOfBoundsException e) {
            String typeOfTask = input.contains("todo")
                    ? "a todo"
                    : input.contains("event")
                    ? "an event"
                    : "a deadline";
            throw new DukeException("\u2639 OOPS!!! The description of " + typeOfTask + " cannot be empty.\n");
        }

        tasks.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task.toString());
        printNumOfTasks();
    }
}
