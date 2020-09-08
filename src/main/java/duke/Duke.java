package duke;

import java.util.StringJoiner;

import task.Deadline;
import task.Event;
import task.Todo;
import ui.TextUi;

/**
 * Duke is the main program where most of the logic is
 * processed. It is a task manager that records tasks
 * based on user input, with the option to list all
 * task, find a particular one, delete tasks, etc.
 */
public class Duke {

    private static final String GREETING =
            "Hi, I'm your Professor, Martin. I keep track of\n"
                    + "your tasks and load them if you have any saved.\n";
    private static final String HELP_MESSAGE =
            "You can ask me to do these:\n"
                    + "list: List the current tasks in your list.\n"
                    + "bye: Saves any tasks in the list and quits the program.\n"
                    + "todo: Add a To-Do task.\n"
                    + "event: Add an event task.\n"
                    + "deadline: Add a deadline task.\n"
                    + "done: Mark task as done based on the task's number.\n"
                    + "delete: Deletes task based on the task's number.\n"
                    + "find: Find a task which matches your description."
                    + "help: Repeat this list of possible commands.";
    private Storage storage;
    private TaskList taskList;
    private TextUi textUi;
    private boolean isRunning;

    /**
     * Initialise Duke with filePath of the saved list of tasks.
     * @param filePath
     */
    public Duke(String filePath) {
        this.textUi = new TextUi();
        this.storage = new Storage(filePath);
        this.isRunning = true;
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            textUi.showException(e);
            taskList = new TaskList();
        }
    }

    /**
     * Main control system which runs Duke in the Command line.
     */
    private void runInConsole() {
        textUi.showMessage(GREETING);
        textUi.showMessage(HELP_MESSAGE);
        textUi.showLine();
        // Loop program until command 'bye' is entered as input.
        while (isRunning) {
            try {
                String input = textUi.readCommand();
                String response = getResponse(input);
                textUi.showMessage(response);
            } catch (DukeException e) {
                textUi.showException(e);
            } finally {
                textUi.showLine();
            }
        }
        textUi.closeUi();
    }

    public String getResponse(String input) throws DukeException {
        StringJoiner outputBuilder = new StringJoiner("\n");
        Parser.Command command = Parser.parseCommand(input);
        switch (command) {
        case BYE:
            isRunning = false;
            if (storage.store(taskList)) {
                outputBuilder.add("Saved your list. Enter new command to exit...");
            } else {
                outputBuilder.add("Failed to save list. Enter new command to exit...");
            }
            break;
        case LIST:
            outputBuilder.add("Here are the tasks in your list:");
            String tasksString = taskList.getAllTasks();
            if (tasksString.length() == 0) {
                outputBuilder.add("No tasks found.");
            } else {
                outputBuilder.add(tasksString);
            }
            break;
        case DONE:
            if (input.substring(4).length() > 1) {
                // For processing "done" command with the corresponding integer value.
                String numString = input.substring(5);
                int entryNum = Integer.parseInt(numString);
                if (taskList.markTaskDone(entryNum)) {
                    outputBuilder.add("Nice! I've marked this task as done:");
                    outputBuilder.add(taskList.getTask(entryNum - 1).toString());
                } else {
                    outputBuilder.add("Failed to mark task as done.");
                }
            } else {
                throw new DukeException("Invalid number for done command.");
            }
            break;
        case DELETE:
            if (input.substring(6).length() > 1) {
                String numString = input.substring(7);
                int entryNum = Integer.parseInt(numString);
                String taskToRemoveString = taskList.getTask(entryNum - 1).toString();
                if (taskList.deleteTask(entryNum)) {
                    outputBuilder.add("Noted. I have removed this task:");
                    outputBuilder.add(taskToRemoveString);
                    outputBuilder.add("Now you have " + taskList.getSize() + " tasks in the list.");
                }
            } else {
                throw new DukeException("Invalid number for delete command.");
            }
            break;
        case TODO:
            if (input.substring(4).length() > 1) {
                String description = input.substring(5);
                Todo d = new Todo(description);
                if (taskList.addTask(d)) {
                    outputBuilder.add("Got it, I've added this todo: " + d);
                    outputBuilder.add("Now you have " + taskList.getSize() + " tasks in the list.");
                } else {
                    outputBuilder.add("Failed to add the todo.");
                }
            } else {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            break;
        case EVENT:
            if (input.substring(5).length() > 1) {
                String description = input.substring(6);
                String[] processedDesc = Parser.parseTimedTask(description);
                Event e = new Event(processedDesc[0], processedDesc[1]);
                if (taskList.addTask(e)) {
                    outputBuilder.add("Got it, I've added this event: " + e);
                    outputBuilder.add("Now you have " + taskList.getSize() + " tasks in the list.");
                } else {
                    outputBuilder.add("Failed to add the event.");
                }
            } else {
                throw new DukeException("The description of an event cannot be empty.");
            }
            break;
        case DEADLINE:
            if (input.substring(8).length() > 1) {
                String description = input.substring(9);
                String[] processedDesc = Parser.parseTimedTask(description);
                Deadline d = new Deadline(processedDesc[0], processedDesc[1]);
                if (taskList.addTask(d)) {
                    outputBuilder.add("Got it, I've added this deadline: " + d);
                    outputBuilder.add("Now you have " + taskList.getSize() + " tasks in the list.");
                } else {
                    outputBuilder.add("Failed to add the deadline.");
                }
            } else {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            break;
        case FIND:
            if (input.substring(4).length() > 1) {
                String description = input.substring(5);
                String matchingTasks = taskList.getMatchingTasks(description);
                if (matchingTasks.length() > 0) {
                    outputBuilder.add("Here are the matching tasks in your list:");
                    outputBuilder.add(matchingTasks);
                } else {
                    outputBuilder.add("No tasks matching your description was found.");
                }
            }
            break;
        case HELP:
            outputBuilder.add(HELP_MESSAGE);
            break;
        // Default for INVALID command.
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return outputBuilder.toString();
    }

    public boolean checkIsRunning() {
        return isRunning;
    }

    public String getGreeting() {
        return GREETING;
    }

    public String getHelpMessage() {
        return HELP_MESSAGE;
    }

    /**
     * Initialise program with a new instance of duke.Duke.
     *
     * @param args String array passed into main.
     */
    public static void main(String[] args) {
        String filePath = System.getProperty("user.dir")
                + (System.getProperty("user.dir").endsWith("text-ui-test")
                ? "\\..\\data\\taskList.txt"
                : "\\data\\taskList.txt");
        Duke dukeProgram = new Duke(filePath);
        dukeProgram.runInConsole();
    }
}
