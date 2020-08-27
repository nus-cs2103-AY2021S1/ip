package sg.christopher.duke;

import sg.christopher.duke.entities.Deadline;
import sg.christopher.duke.entities.Event;
import sg.christopher.duke.entities.Task;
import sg.christopher.duke.entities.Todo;
import sg.christopher.duke.io.DataManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<Task> savedItems = loadSavedItems();

    public static List<Task> loadSavedItems() {
        List<Task> saved = DataManager.readList();
        return saved != null ? saved : new ArrayList<>();
    }

    public static void saveItem(Task task) {
        savedItems.add(task);
        DataManager.writeList(savedItems);
    }
    
    public static Task loadItem(int index) {
        savedItems = DataManager.readList();
        return savedItems.get(index);
    }

    private static void doneHandler(String userInput) {
        int taskNo;
        try {
            taskNo = Integer.parseInt(userInput.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            System.out.println("ERROR: No task no. found. Did you input the task no. of the task you'd like to mark as done?");
            return;
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR: Unrecognized task. Please input the task no. of the task you'd like to mark as done.");
            return;
        }
        Task task;
        try {
            task = loadItem(taskNo - 1);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("ERROR: Task no. not found. Does that task exist?");
            return;
        }
        task.markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println(taskNo + ". " + task);
    }

    private static void todoHandler(String userInput) {
        // Check for description
        if (userInput.split(" ").length < 2) {
            System.out.println("ERROR: Description of todo cannot be empty.");
            return;
        }
        String description = userInput.replaceFirst("todo ", "");
        Todo todo = new Todo(description);
        saveItem(todo);
        System.out.println("Got it. I've added this task:");
        System.out.println(todo);
        printRemainingCount();
    }

    private static void deadlineHandler(String userInput) {
        // Check for description
        if (userInput.split(" ").length < 2) {
            System.out.println("ERROR: Description of deadline cannot be empty.");
            return;
        }
        String[] input = userInput.replaceFirst("deadline ", "").split(" /by ");

        // Check for deadline in description
        if (input.length < 2) {
            System.out.println("ERROR: Deadline not found. Did you input a deadline with `/by`?");
            return;
        } else if (input.length > 2) {
            System.out.println("ERROR: Multiple deadlines found. Please only input one deadline.");
            return;
        }
        Deadline deadline = new Deadline(input[0], input[1]);
        saveItem(deadline);
        System.out.println("Got it. I've added this task:");
        System.out.println(deadline);
        printRemainingCount();
    }

    private static void eventHandler(String userInput) {
        // Check for description
        if (userInput.split(" ").length < 2) {
            System.out.println("ERROR: Description of event cannot be empty.");
            return;
        }
        String[] input = userInput.replaceFirst("event ", "").split(" /at ");

        // Check for dateTime in description
        if (input.length < 2) {
            System.out.println("ERROR: Date/time not found. Did you input a date/time with `/at`?");
            return;
        } else if (input.length > 2) {
            System.out.println("ERROR: Multiple date/times found. Please only input one date/time.");
            return;
        }

        Event event = new Event(input[0], input[1]);
        saveItem(event);
        System.out.println("Got it. I've added this task:");
        System.out.println(event);
        printRemainingCount();
    }

    private static void lsHandler() {
        if (savedItems.size() == 0) {
            System.out.println("No tasks found. Start adding your first few tasks!");
            return;
        }
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < savedItems.size(); ++i) {
            Task task = loadItem(i);
            System.out.println(i + 1 + ". " + task);
        }
        printRemainingCount();
    }

    private static void deleteHandler(String userInput) {
        int taskNo;
        try {
            taskNo = Integer.parseInt(userInput.split(" ")[1]);
        } catch (ArrayIndexOutOfBoundsException aioobe) {
            System.out.println("ERROR: No task no. found. Did you input the task no. of the task you'd like to delete?");
            return;
        } catch (NumberFormatException nfe) {
            System.out.println("ERROR: Unrecognized task. Please input the task no. of the task you'd like to delete.");
            return;
        }

        Task task;
        try {
            task = savedItems.remove(taskNo - 1);
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("ERROR: Task no. not found. Does that task exist?");
            return;
        }

        System.out.println("Noted. I've removed this task:");
        System.out.println(taskNo + ". " + task);
        printRemainingCount();
    }

    private static void printRemainingCount() {
        System.out.println("You now have " + savedItems.size() + " tasks in the list.");
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

    public static void main(String[] args) {
        System.out.println("Hello I'm Duke!");
        System.out.println("What can I do for you?");

        String userInput;
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            userInput = scanner.nextLine();
            String command = userInput.split(" ")[0];

            CommandType commandType = getCommandType(command);

            switch (commandType) {
            case EXIT:
                System.out.println("Bye. Hope to see you again soon!");
                return;
            case TODO:
                todoHandler(userInput);
                break;
            case DEADLINE:
                deadlineHandler(userInput);
                break;
            case EVENT:
                eventHandler(userInput);
                break;
            case DELETE:
                deleteHandler(userInput);
                break;
            case DONE:
                doneHandler(userInput);
                break;
            case LIST:
                lsHandler();
                break;
            case UNRECOGNISED:
                System.out.println("ERROR: Unrecognised command. Did you make a typo?");
                break;
            }
        }
    }
}
