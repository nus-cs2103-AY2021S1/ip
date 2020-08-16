import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Duke {

    public static ArrayList<Task> taskList = new ArrayList<>();

    /**
     * Main control system which loops user input until user exits with the
     * command 'bye'.
     *
     * @param args args supplied to main.
     */
    public static void main(String[] args) {
        String introduction =
                "Hi, I'm your Professor, Martin." +
                        "\nWhat can I do for you?";
        System.out.println(introduction);
        Scanner sc = new Scanner(System.in);
        String input;

        // Loop program until command 'bye' is entered as input.
        while (true) {
            try {
                System.out.print("Enter input: ");
                input = sc.nextLine();
                if (input.equals("bye")) {
                    System.out.println("Bye, see you soon. Exiting...");
                    break;
                } else if (input.equals("list")) {
                    listAllTasks();
                } else if (input.length() >= 8 && input.substring(0, 8).equals("deadline")) {
                    if (input.substring(8).length() > 1) {
                        String description = input.substring(9);
                        String[] processedDesc = processTimedTask(description);
                        Deadline d = new Deadline(processedDesc[0], processedDesc[1]);
                        addTask(d);
                    } else {
                        throw new DukeException("The description of a deadline cannot be empty.");                    }
                } else if (input.length() >= 5 && input.substring(0, 5).equals("event")) {
                    if (input.substring(5).length() > 1) {
                        String description = input.substring(6);
                        String[] processedDesc = processTimedTask(description);
                        Event e = new Event(processedDesc[0], processedDesc[1]);
                        addTask(e);
                    } else {
                        throw new DukeException("The description of an event cannot be empty.");
                    }
                } else if (input.length() >= 4) {
                    String command = input.substring(0, 4);
                    // Navigate to either done or to-do.
                    switch (command) {
                        case "done":
                            // For processing "done" command with the corresponding integer value.
                            String numString = input.substring(5);
                            int entryNum = Integer.parseInt(numString);
                            markEntryDone(entryNum);
                            break;
                        case "todo":
                            if (input.substring(4).length() > 1) {
                                String description = input.substring(5);
                                Todo d = new Todo(description);
                                addTask(d);
                            } else {
                                throw new DukeException("The description of a todo cannot be empty.");
                            }
                            break;
                        default:
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");                }
            } catch (DukeException e) {
                System.out.println("Exception occurred during main loop: " + e);
            }
        }
        sc.close();
    }

    /**
     * Lists all tasks currently stored in the system.
     */
    public static void listAllTasks() {
        int numEntries = taskList.size();
        if (numEntries == 0) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < numEntries; i++) {
                System.out.println((i + 1) + ". " + taskList.get(i));
            }
        }
    }

    /**
     * Stores task into the ArrayList.
     *
     * @param t Task to be stored into the ArrayList.
     */
    public static void addTask(Task t) {
        taskList.add(t);
        System.out.println("Got it, I've added this task: " + t);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Given a particular task number, mark that task in the task list as done.
     * as done.
     *
     * @param taskNum The task number to mark as done.
     */
    public static void markEntryDone(int taskNum) {
        try {
            if (taskNum < 0 || taskNum > taskList.size()) {
                throw new DukeException("Task number does not exist.");
            } else {
                Task t = taskList.get(taskNum - 1);
                t.markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t);
            }
        } catch (DukeException e) {
            System.out.println("Exception occurred while marking task as done: " + e);
        }
    }

    /**
     * Helper function to process a timed task description (Event, Deadline) and
     * split it into 2 strings, the description and the task date.
     *
     * @param str The raw string to be processed.
     * @return A String array of size 2. Index 0 contains the task's description,
     * index 1 contains the date of the task.
     */
    public static String[] processTimedTask(String str) {
        String[] result = new String[2];
        try {
            // Split the string first then loop through to find the stop point at either /at or /by.
            String[] arr = str.split(" ");
            int indexToStop = -1;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals("/at") || arr[i].equals("/by")) {
                    indexToStop = i;
                }
            }
            if (indexToStop == -1) throw new DukeException("Incorrect Input for timed task.");

            // Use StringBuilder Class to recreate the description and time separately.
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < indexToStop; j++) {
                sb.append(arr[j]);
                sb.append(" ");
            }
            result[0] = sb.toString();

            sb = new StringBuilder();
            for (int k = indexToStop + 1; k < arr.length; k++) {
                sb.append(arr[k]);
                sb.append(" ");
            }
            result[1] = sb.toString();
        } catch (DukeException e) {
            System.out.println("Exception occurred while processing timed task: " + e);
        }
        return result;
    }
}
