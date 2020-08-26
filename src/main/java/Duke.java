
import java.io.File;
import java.io.FileWriter;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private ArrayList<Task> taskList;
    private boolean isRunning;

    private enum Command {
        BYE, LIST, DONE, DELETE, TODO, EVENT, DEADLINE, FIND, INVALID
    }

    public Duke() {
        this.taskList = new ArrayList<>();
        this.isRunning = true;
    }

    /**
     * Take the first keyword from the user's input, and return
     * an integer corresponding to the right command.
     *
     * @param input
     * @return
     */
    private Command getCommand(String input) {
        String lowerCaseInput = input.toLowerCase();
        switch (lowerCaseInput) {
            case "bye":
                return Command.BYE;
            case "list":
                return Command.LIST;
            case "done":
                return Command.DONE;
            case "delete":
                return Command.DELETE;
            case "todo":
                return Command.TODO;
            case "event":
                return Command.EVENT;
            case "deadline":
                return Command.DEADLINE;
            case "find":
                return Command.FIND;
            default:
                return Command.INVALID;
        }
    }

    /**
     * Main control system which loops user input until user exits with the
     * command 'bye'.
     */
    private void run() {
        // TODO Need to GET saved list file and process it into ArrayList.
        openTaskList();
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
                Command command = getCommand(input.split(" ")[0]);
                switch (command) {
                    case BYE:
                        // TODO Need to implement saving mechanism.
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
     * Store task into the ArrayList.
     *
     * @param t Task to be stored into the ArrayList.
     */
    private void addTask(Task t) {
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
    private void markTaskDone(int taskNum) {
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

    private void deleteTask(int taskNum) {
        try {
            if (taskNum > 0 && taskNum <= taskList.size()) {
                System.out.println("Noted. I have removed this task:");
                System.out.println(taskList.get(taskNum - 1));
                taskList.remove(taskNum - 1);
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
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
                }
            }
            if (indexToStop == -1) throw new DukeException("Incorrect Input for timed task.");

            // Use StringBuilder Class to recreate the description and time separately.
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < indexToStop; j++) {
                sb.append(arr[j]);
                if (j != indexToStop - 1) sb.append(" ");
            }
            result[0] = sb.toString();

            sb = new StringBuilder();
            for (int k = indexToStop + 1; k < arr.length; k++) {
                sb.append(arr[k]);
                if (k != arr.length - 1) sb.append(" ");
            }
            result[1] = sb.toString();
        } catch (DukeException e) {
            System.out.println("Exception occurred while processing timed task: " + e);
        }
        return result;
    }

    /**
     * Get task list from the taskList.txt and transfer the contents
     * to the taskList ArrayList.
     */
    private void openTaskList() {
        try {
            File savedList = new File("src/main/data/taskList.txt");
            if (!savedList.exists()) {
                System.out.print(
                        "Welcome, first time user. Let me create " +
                                "a new database to store your tasks.."
                );
                System.out.println(savedList.createNewFile() ? " Success." : " Failure.");
            } else {
                System.out.println("Found your data! Give me some time to read it...");
                // TODO Get content from file. Transfer to arraylist.
                Scanner taskReader = new Scanner(savedList);
                while (taskReader.hasNextLine()) {
                    String taskFromFile = taskReader.nextLine();
                    // Note, this is assuming that format of
                    // Task.getDescriptionForDatabase() remains the same.
                    String[] formattedTaskString = taskFromFile.split(" - ");
                    Command taskCommand = getCommand(formattedTaskString[0]);
                    boolean isTaskDone = formattedTaskString[1].equals("0");
                    switch (taskCommand) {
                        case TODO:
                            taskList.add(new Todo(formattedTaskString[2], isTaskDone));
                            break;
                        case EVENT:
                            taskList.add(
                                    new Event(
                                            formattedTaskString[2],
                                            formattedTaskString[3],
                                            isTaskDone
                                    )
                            );
                            break;
                        case DEADLINE:
                            taskList.add(
                                    new Deadline(
                                            formattedTaskString[2],
                                            formattedTaskString[3],
                                            isTaskDone
                                    )
                            );
                            break;
                    }
                }
                taskReader.close();
            }

        } catch (
                Exception exception) {
            System.out.println("Exception while opening task list file:" + exception);
        }
    }

    /**
     * Save the current list into a file and closes the program
     * by setting isRunning to false.
     */
    private void closeDuke() {
        isRunning = false;
        // Begin process of saving Tasks in the taskList to the file.
        try {
            if (taskList.size() > 0) {
                File savedList = new File("src/main/data/taskList.txt");
                FileWriter taskWriter = new FileWriter(savedList);

                for (Task t : taskList) {
                    taskWriter.write(t.getDescriptionForDatabase());
                    taskWriter.write("\n");
                }
                System.out.println("...Saved your list.");
                taskWriter.close();
            }
        } catch (Exception e) {
            System.out.println("Exception occurred while closing Duke: " + e.toString());
        }
        System.out.println("Bye, see you soon. Exiting...");
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
