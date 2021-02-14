package sg.christopher.duke;

import javafx.application.Platform;
import javafx.stage.Stage;
import sg.christopher.duke.entities.Deadline;
import sg.christopher.duke.entities.Event;
import sg.christopher.duke.entities.Task;
import sg.christopher.duke.entities.Todo;
import sg.christopher.duke.io.DataManager;
import sg.christopher.duke.ui.MainWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Duke {

    private static List<Task> savedItems = loadSavedItems();

    private MainWindow mainWindowController;
    private static Stage stage;

    public void setMainWindowController(MainWindow mw) {
        mainWindowController = mw;
    }

    public void setStage(Stage stage) {
        Duke.stage = stage;
    }

    private void dukePrint(String message) {
        mainWindowController.printDukeMessage(message);
    }

    public void printWelcome() {
        dukePrint("Hello I'm Duke! What can I do for you?");
    }

    /**
     * Hydrates the task data store with saved data from disk.
     *
     * @return list of tasks that are saved on disk
     */
    public static List<Task> loadSavedItems() {
        List<Task> saved = DataManager.readList();
        return saved != null ? saved : new ArrayList<>();
    }

    /**
     * Saves a task to memory and writes it to disk.
     *
     * @param task the task to be saved
     */
    public static void saveItem(Task task) {
        savedItems.add(task);
        DataManager.writeList(savedItems);
    }

    /**
     * Loads saved tasks from disk, and get a specific task using its index.
     *
     * @param index index of the task to be retrieved
     * @return the task at that index
     */
    public static Task loadItem(int index) {
        savedItems = DataManager.readList();
        return savedItems.get(index);
    }

    /**
     * Removes saved tasks from disk, using its index.
     *
     * @param index index of the task to be removed
     * @return task that is removed
     */
    public static Task removeItem(int index) {
        Task removed = savedItems.remove(index);
        DataManager.writeList(savedItems);
        return removed;
    }

    private static String doneHandler(String userInput) {
        int taskNo;
        try {
            taskNo = Integer.parseInt(userInput.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            return "ERROR: No task no. found. Did you input the task no. of the task you'd like to mark as done?";
        } catch (NumberFormatException nfe) {
            return "ERROR: Unrecognized task. Please input the task no. of the task you'd like to mark as done.";
        }

        Task task;
        try {
            task = loadItem(taskNo - 1);
        } catch (IndexOutOfBoundsException ioobe) {
            return "ERROR: Task no. not found. Does that task exist?";
        }
        task.markAsDone();
        DataManager.writeList(savedItems);

        return "Nice! I've marked this task as done:\n" + taskNo + ". " + task;
    }

    private static String todoHandler(String userInput) {
        // Check for description
        if (userInput.split(" ").length < 2) {
            return "ERROR: Description of todo cannot be empty.";
        }
        String description = userInput.replaceFirst("todo ", "");
        Todo todo = new Todo(description);
        saveItem(todo);
        return "Got it. I've added this task:\n" + todo + printRemainingCount();
    }

    private static String deadlineHandler(String userInput) {
        // Check for description
        if (userInput.split(" ").length < 2) {
            return "ERROR: Description of deadline cannot be empty.";
        }
        String[] input = userInput.replaceFirst("deadline ", "").split(" /by ");

        // Check for deadline in description
        if (input.length < 2) {
            return "ERROR: Deadline not found. Did you input a deadline with `/by`?";
        } else if (input.length > 2) {
            return "ERROR: Multiple deadlines found. Please only input one deadline.";
        }
        Deadline deadline = new Deadline(input[0], input[1]);
        saveItem(deadline);
        return "Got it. I've added this task:\n" + deadline + printRemainingCount();
    }

    private static String eventHandler(String userInput) {
        // Check for description
        if (userInput.split(" ").length < 2) {
            return "ERROR: Description of event cannot be empty.";
        }
        String[] input = userInput.replaceFirst("event ", "").split(" /at ");

        // Check for dateTime in description
        if (input.length < 2) {
            return "ERROR: Date/time not found. Did you input a date/time with `/at`?";
        } else if (input.length > 2) {
            return "ERROR: Multiple date/times found. Please only input one date/time.";
        }

        Event event = new Event(input[0], input[1]);
        saveItem(event);
        return "Got it. I've added this task:\n" + event + printRemainingCount();
    }

    private static String findHandler(String userInput) {
        // Check for search term
        if (userInput.split(" ").length < 2) {
            return "ERROR: Search term not found. Did you type a search term?";
        }
        String searchTerm = userInput.replaceFirst("find ", "").toLowerCase();

        savedItems = loadSavedItems();

        List<Task> foundTasks = savedItems.stream().filter(task -> task.getDescription().toLowerCase().contains(searchTerm)).collect(Collectors.toList());

        if (foundTasks.size() == 0) {
            return "No task matching your search term was found. Perhaps try another search term?";
        }
        StringBuilder sb = new StringBuilder("Here are the matching tasks in your list:\n");
        for (int i = 0; i < foundTasks.size(); ++i) {
            Task task = foundTasks.get(i);
            sb.append(i + 1 + ". " + task + "\n");
        }
        return sb.toString();
    }

    private static String lsHandler() {
        if (savedItems.size() == 0) {
            return "No tasks found. Start adding your first few tasks!";
        }
        assert savedItems.size() >= 1;
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        for (int i = 0; i < savedItems.size(); ++i) {
            Task task = loadItem(i);
            sb.append(i + 1 + ". " + task + "\n");
        }
        sb.append(printRemainingCount());
        return sb.toString();
    }

    private static String deleteHandler(String userInput) {
        int taskNo;
        try {
            taskNo = Integer.parseInt(userInput.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            return "ERROR: No task no. found. Did you input the task no. of the task you'd like to delete?";
        } catch (NumberFormatException nfe) {
            return "ERROR: Unrecognized task. Please input the task no. of the task you'd like to delete.";
        }

        Task task;
        try {
            task = removeItem(taskNo - 1);
        } catch (IndexOutOfBoundsException ioobe) {
            return "ERROR: Task no. not found. Does that task exist?";
        }

        return "Noted. I've removed this task:\n" + taskNo + ". " + task + printRemainingCount();
    }

    private static String printRemainingCount() {
        return "\nYou now have " + savedItems.size() + " tasks in the list.";
    }

    private static CommandType getCommandType(String command) {
        switch (command) {
        case "bye":
            // Fallthrough
        case "exit":
            return CommandType.EXIT;
        case "todo":
            return CommandType.TODO;
        case "deadline":
            return CommandType.DEADLINE;
        case "event":
            return CommandType.EVENT;
        case "find":
            return CommandType.FIND;
        case "rm":
            // Fallthrough
        case "delete":
            return CommandType.DELETE;
        case "done":
            return CommandType.DONE;
        case "ls":
            return CommandType.LIST;
        default:
            return CommandType.UNRECOGNISED;
        }
    }

    public static String getResponse(String userInput) {
        String command = userInput.split(" ")[0];

        CommandType commandType = getCommandType(command);

        switch (commandType) {
        case EXIT:
            new Thread(() -> {
                try {
                    Thread.sleep(1500);
                    Platform.runLater(() -> stage.close());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
            return "Bye. Hope to see you again soon!";
        case TODO:
            return todoHandler(userInput);
        case DEADLINE:
            return deadlineHandler(userInput);
        case EVENT:
            return eventHandler(userInput);
        case DELETE:
            return deleteHandler(userInput);
        case FIND:
            return findHandler(userInput);
        case DONE:
            return doneHandler(userInput);
        case LIST:
            return lsHandler();
        case UNRECOGNISED:
            return "ERROR: Unrecognised command. Did you make a typo?";
        }
        return null;
    }
}
