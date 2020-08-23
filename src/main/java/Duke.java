import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

/**
 * Serves as a chat bot. Duke can keep a record of user's inputs as a list of
 * tasks, mark them as completed when they are done, and show the user the list
 * of tasks upon request.
 */
public class Duke {

    /**
     * Star Bot's logo shown upon start up
     */
    private static final String LOGO = "     _______.___________.    ___     " +
            " .______      \n" +
            "    /       |           |   /   \\     |   _  \\     \n" +
            "   |   (----`---|  |----`  /  ^  \\    |  |_)  |    \n" +
            "    \\   \\       |  |      /  /_\\  \\   |      /     \n" +
            ".----)   |      |  |     /  _____  \\  |  |\\  \\----.\n" +
            "|_______/       |__|    /__/     \\__\\ | _| `._____|\n" +
            "                                                   \n" +
            "         .______     ______   .___________.        \n" +
            "         |   _  \\   /  __  \\  |           |        \n" +
            "         |  |_)  | |  |  |  | `---|  |----`        \n" +
            "         |   _  <  |  |  |  |     |  |             \n" +
            "         |  |_)  | |  `--'  |     |  |             \n" +
            "         |______/   \\______/      |__|             \n" +
            "                                                   ";

    /**
     * Divider that delineates Star Bot's replies
     */
    private static final String DIVIDER =
            "------------------------------------------------------\n";

    private static String memoryFilePath = "data/duke.txt";

    /**
     * Stores user's tasks
     */
    private static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            loadTaskListFromMemory();
        } catch (FileNotFoundException | WrongFormatException e) {
            System.out.println(botReply(e.getMessage()));
        }

        System.out.println(LOGO + "\nHello, I'm Star Bot! What can I do for " +
                "you?\nSay \"bye\" to exit.\n");

        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) { // Duke takes in user input indefinitely until the user says "bye"
            String line = sc.nextLine();
            String[] splitLine = line.split(" ");
            boolean isListChanged = false;
            try {
                if (line.equals("bye")) { // Exit the program
                    System.out.println(botReply("Goodbye, see you again soon!" +
                            " :)"));
                    System.exit(0);
                } else if (line.equals("list")) { // List out task list
                    System.out.println(botReply(printList()));
                } else if (splitLine[0].equals("done")) { // Done with a task
                    try {
                        if (splitLine.length != 2) { // If command is in a wrong
                            // format
                            throw new DoneWrongFormatException();
                        }
                        int taskIndex = Integer.parseInt(splitLine[1]) - 1;
                        // Index of task in the task list
                        Task completedTask = taskList.get(taskIndex);
                        completedTask.markAsDone();
                        isListChanged = true;
                        System.out.println(botReplyForDoneTask(completedTask));
                    } catch (NumberFormatException e) { // Second argument of
                        // command was not a number, e.g. "done X"
                        throw new DoneWrongFormatException();
                    } catch (IndexOutOfBoundsException e) { // User requests
                        // for a task with an index not within the current
                        // task list
                        throw new TaskIndexOutOfBoundsException(splitLine[1]);
                    }
                } else if (splitLine[0].equals("delete")) { // Delete a task
                    try {
                        if (splitLine.length != 2) { // If command is in a wrong
                            // format
                            throw new DeleteWrongFormatException();
                        }
                        int taskIndex = Integer.parseInt(splitLine[1]) - 1;
                        // Index of task in the task list
                        Task removedTask = taskList.remove(taskIndex);
                        isListChanged = true;
                        System.out.println(botReplyForDeleteTask(removedTask));
                    } catch (NumberFormatException e) { // Second argument of
                        // command was not a number, e.g. "delete X"
                        throw new DeleteWrongFormatException();
                    } catch (IndexOutOfBoundsException e) { // User requests
                        // for a task with an index not within the current
                        // task list
                        throw new TaskIndexOutOfBoundsException(splitLine[1]);
                    }
                } else if (splitLine[0].equals("todo")) { // Add To-Do task
                    try {
                        Task newTask = new ToDo(line.substring(5).trim());
                        taskList.add(newTask);
                        isListChanged = true;
                        System.out.println(botReplyForAddTask(newTask));
                    } catch (IndexOutOfBoundsException | WrongFormatException e)
                    { // Command is in a wrong format
                        throw new TodoWrongFormatException();
                    }
                } else if (splitLine[0].equals("event")) { // Add Event task
                    try {
                        String[] splitLineIntoTwo = line.split("/at");
                        Task newTask = new Event(splitLineIntoTwo[0]
                                .substring(6).trim(),
                                splitLineIntoTwo[1].trim());
                        taskList.add(newTask);
                        isListChanged = true;
                        System.out.println(botReplyForAddTask(newTask));
                    } catch (IndexOutOfBoundsException | WrongFormatException e)
                    { // Command is in a wrong format
                        throw new EventWrongFormatException();
                    }
                } else if (splitLine[0].equals("deadline")) { // Add Deadline
                    // task
                    try {
                        String[] splitLineIntoTwo = line.split("/by");
                        Task newTask = new Deadline(splitLineIntoTwo[0]
                                .substring(9).trim(),
                                splitLineIntoTwo[1].trim());
                        taskList.add(newTask);
                        isListChanged = true;
                        System.out.println(botReplyForAddTask(newTask));
                    } catch (IndexOutOfBoundsException | WrongFormatException | DateTimeException e)
                    { // Command is in a wrong format
                        throw new DeadlineWrongFormatException();
                    }
                } else { // Unknown command entered
                    throw new UnknownCommandException();
                }
            } catch (DukeException e) {
                System.out.println(botReply(e.defaultErrorMessage()));
            }
            if (isListChanged) {
                try {
                    writeToFile();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Standardises the look of Star Bot's replies by wrapping it in
     * dividers
     */
    private static String botReply(String reply) {
        return DIVIDER + reply + "\n" + DIVIDER;
    }

    private static String botReplyForAddTask(Task newTask) {
        return botReply("Got it. I've added this task:\n" + newTask + "\nNow " +
                "you have " + taskList.size() + " tasks in the list.");
    }

    private static String botReplyForDoneTask(Task doneTask) {
        return botReply("Nice! I've marked this task as done:\n" + doneTask);
    }

    private static String botReplyForDeleteTask(Task removedTask) {
        return botReply("Noted. I've removed this task:\n" + removedTask +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Formats the task list to be shown to the user.
     */
    private static String printList() {
        if (taskList.isEmpty()) {
            return "Your list is empty! Let's add some tasks!";
        }
        int index = 1;
        StringBuilder result = new StringBuilder("Here are the tasks in your " +
                "list:");
        for (Task task : taskList) {
            result.append("\n").append(index++).append(".").append(task);
        }
        return result.toString();
    }

    /**
     * Loads the task list from memory (path specified by memoryFilePath).
     * @throws FileNotFoundException
     * @throws WrongFormatException
     */
    private static void loadTaskListFromMemory() throws FileNotFoundException, WrongFormatException {
        File memoryFile = new File(memoryFilePath);
        if (!memoryFile.exists()) { // If file is non-existent, either the .txt file or a directory in the
            // memoryFilePath does not exist
            boolean areExistentDirectories = true; // Check if all directories specified in memoryFilePath exist
            String[] splitPath = memoryFilePath.split("/");
            String testPath = "";
            String nonExistentDirectory = "";
            for (int i = 0; i < splitPath.length - 1; i++) {
                testPath += splitPath[i] + "/";
                if (!new File(testPath).exists()) { // A directory specified in memoryFilePath does not exist
                    areExistentDirectories = false;
                    nonExistentDirectory = splitPath[i]; // The directory specified in memoryFilePath that does not
                    // exist
                    break;
                }
            }
            if (areExistentDirectories) { // All directories specified in memoryFilePath exists, only the .txt file
                // does not exist
                try {
                    Files.createFile(Path.of(memoryFilePath)); // Create the .txt file with location as stated in
                    // memoryFilePath
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
                throw new FileNotFoundException("ERROR: Could not load last save.\nThe save file \""
                        + splitPath[splitPath.length - 1] + "\" does not exist.\nNow loading a new, empty task list" +
                        ".\nA new save file \"" + splitPath[splitPath.length - 1] + "\" has been created with the\n" +
                        "following path: \"" + memoryFilePath + "\".");
            } else { // A directory specified in memoryFilePath does not exist so we should create it (and all its
                // subdirectories if any)
                try {
                    String fullPath = "";
                    for (int i = 0; i < splitPath.length - 1; i++) {
                        fullPath += splitPath[i] + "/";
                    }
                    Files.createDirectories(Path.of(fullPath)); // Create missing directory (and all its
                    // subdirectories if any) as specified by memoryFilePath
                    Files.createFile(Path.of(memoryFilePath)); // Create .txt file in newly created path
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
                throw new FileNotFoundException("ERROR: Could not load last save.\nPath specified for save file: " +
                        "\"" + memoryFilePath + "\"\nThe directory \"" + nonExistentDirectory + "\"\n(and hence all " +
                        "subdirectories of it, if any)\ndoes not exist.\nNow loading a new, empty task list.\nA new " +
                        "save file \"" + splitPath[splitPath.length - 1] + "\" has been created with the\nfollowing " +
                        "path: \"" + memoryFilePath + "\".");
            }
        }

        // If the save file exists, we load the task list with tasks as specified in the save file
        Scanner sc = new Scanner(memoryFile);
        while (sc.hasNextLine()) {
            String[] splitLine = sc.nextLine().split("\\|");
            switch (splitLine[0]) {
            case "[T]": // To-Do
                taskList.add(new ToDo(splitLine[2], !splitLine[1].equals("0")));
                break;
            case "[E]": // Event
                taskList.add(new Event(splitLine[2], splitLine[3], !splitLine[1].equals("0")));
                break;
            case "[D]": // Deadline
                taskList.add(new Deadline(splitLine[2], LocalDateTime.parse(splitLine[3]).format(DateTimeFormatter
                        .ofPattern("yyyy-MM-dd HHmm")), !splitLine[1].equals("0")));
                break;
            default:
                System.out.println(botReply("Error in last save. Now loading a new, empty task list."));
                break;
            }
        }
    }

    /**
     * Writes to save file as specified by memoryFilePath. Writing to save occurs every time the task list changes.
     * @throws IOException
     */
    private static void writeToFile() throws IOException {
        FileWriter fw = new FileWriter(memoryFilePath);
        for (Task task : taskList) {
            fw.write(task.stringToSaveInMemory() + "\n");
        }
        fw.close();
    }
}
