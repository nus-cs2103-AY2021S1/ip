
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private String filePath;
    private boolean isRunning;


    public Duke() {
        this.ui = new Ui();
        String filePath = System.getProperty("user.dir")
                + (System.getProperty("user.dir").endsWith("text-ui-test")
                ? "\\..\\data\\taskList.txt"
                : "\\data\\taskList.txt");
        this.storage = new Storage(filePath);
        this.isRunning = true;
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }


    /**
     * Main control system which loops user input until user exits with the
     * command 'bye'.
     */
    private void run() {
        String introduction =
                "Hi, I'm your Professor, Martin." +
                        "\nWhat can I do for you? You can ask me to do these:" +
                        "\nlist: List the current tasks in your list." +
                        "\ntodo: Add a To-Do task." +
                        "\nevent: Add an Event task." +
                        "\ndeadline: Add a Deadline task." +
                        "\nfind: Find a task which matches your description.";
        System.out.println(introduction);
        Scanner sc = new Scanner(System.in);
        String input;

        // Loop program until command 'bye' is entered as input.
        while (isRunning) {
            try {
                System.out.println("Enter input:");
                input = sc.nextLine();
                CommandEnum.Command command = CommandEnum.getCommand(input.split(" ")[0]);
                switch (command) {
                    case BYE:
                        this.isRunning = false;
                        closeDuke();
                        break;
                    case LIST:
                        listAllTasks();
                        break;
                    case DONE:
                        if (input.substring(4).length() > 1) {
                            // For processing "done" command with the corresponding integer value.
                            String numString = input.substring(5);
                            int entryNum = Integer.parseInt(numString);
                            markTaskDone(entryNum);
                        } else {
                            throw new DukeException("Invalid number for done command.");
                        }
                        break;
                    case DELETE:
                        if (input.substring(6).length() > 1) {
                            String numString = input.substring(7);
                            int entryNum = Integer.parseInt(numString);
                            deleteTask(entryNum);
                        } else {
                            throw new DukeException("Invalid number for delete command.");
                        }
                        break;
                    case TODO:
                        if (input.substring(4).length() > 1) {
                            String description = input.substring(5);
                            Todo d = new Todo(description);
                            addTask(d);
                        } else {
                            throw new DukeException("The description of a todo cannot be empty.");
                        }
                        break;
                    case EVENT:
                        if (input.substring(5).length() > 1) {
                            String description = input.substring(6);
                            String[] processedDesc = processTimedTask(description);
                            Event e = new Event(processedDesc[0], processedDesc[1]);
                            addTask(e);
                        } else {
                            throw new DukeException("The description of an event cannot be empty.");
                        }
                        break;
                    case DEADLINE:
                        if (input.substring(8).length() > 1) {
                            String description = input.substring(9);
                            String[] processedDesc = processTimedTask(description);
                            Deadline d = new Deadline(processedDesc[0], processedDesc[1]);
                            addTask(d);
                        } else {
                            throw new DukeException("The description of a deadline cannot be empty.");
                        }
                        break;
                    case FIND:
                        break;

                    // Default for INVALID command.
                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println("Exception occurred during main loop: " + e);
            }
        }
        sc.close();
    }

    /**
     * List all tasks currently stored in the system.
     */
    private void listAllTasks() {
        int numEntries = tasks.size();
        if (numEntries == 0) {
            System.out.println("No tasks found.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < numEntries; i++) {
                System.out.println((i + 1) + ". " + tasks.get(i));
            }
        }
    }

    /**
     * Store task into the ArrayList.
     *
     * @param t Task to be stored into the ArrayList.
     */
    private void addTask(Task t) {
        tasks.add(t);
        System.out.println("Got it, I've added this task: " + t);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
    }

    /**
     * Given a particular task number, mark that task in the task list as done.
     * as done.
     *
     * @param taskNum The task number to mark as done.
     */
    private void markTaskDone(int taskNum) {
        try {
            if (taskNum < 0 || taskNum > tasks.size()) {
                throw new DukeException("Task number does not exist.");
            } else {
                Task t = tasks.get(taskNum - 1);
                t.markDone();
                System.out.println("Nice! I've marked this task as done:");
                System.out.println(t);
            }
        } catch (DukeException e) {
            System.out.println("Exception occurred while marking task as done: " + e);
        }
    }

    private void deleteTask(int taskNum) {
        try {
            if (taskNum > 0 && taskNum <= tasks.size()) {
                System.out.println("Noted. I have removed this task:");
                System.out.println(tasks.get(taskNum - 1));
                tasks.remove(taskNum - 1);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new DukeException("Invalid task number for current task list.");
            }
        } catch (DukeException e) {
            System.out.println("Exception occurred while deleting task: " + e);
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
    private String[] processTimedTask(String str) {
        String[] result = new String[2];
        try {
            // Split the string first then loop through to find the stop point at either /at or /by.
            String[] arr = str.split(" ");
            int indexToStop = -1;
            for (int i = 0; i < arr.length; i++) {
                if (arr[i].equals("/at") || arr[i].equals("/by")) {
                    indexToStop = i;
                    // Stop index when indicator /at or /by is found.
                }
            }
            if (indexToStop == -1) throw new DukeException("Incorrect Input for timed task.");

            // Use StringBuilder Class to recreate the description and time separately.
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < indexToStop; j++) {
                sb.append(arr[j]);
                if (j != indexToStop - 1) sb.append(" ");
            }
            // Event / Deadline description has been built, pass it to result[0].
            result[0] = sb.toString();

            sb = new StringBuilder();
            for (int k = indexToStop + 1; k < arr.length; k++) {
                sb.append(arr[k]);
                if (k != arr.length - 1) sb.append(" ");
            }
            String date = sb.toString();
            // Now to check if this date can be formatted nicely using DateTimeProcessor class.
            String parsedDate = new DateTimeProcessor().getParsedDate(date);
            result[1] = parsedDate;
        } catch (DukeException e) {
            System.out.println("Exception occurred while processing timed task: " + e);
        }
        return result;
    }


    /**
     * Save the current list into a file and closes the program
     */
    private void closeDuke() {
        this.storage.store(this.tasks);
    }

    /**
     * Initialise program with a new instance of Duke.
     *
     * @param args String array passed into main.
     */
    public static void main(String[] args) {
        Duke dukeProgram = new Duke();
        dukeProgram.run();
    }
}
