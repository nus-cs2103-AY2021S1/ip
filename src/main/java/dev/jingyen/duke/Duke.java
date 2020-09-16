package dev.jingyen.duke;

import java.io.File;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import dev.jingyen.duke.model.Task;
import dev.jingyen.duke.parser.InvalidInputException;
import dev.jingyen.duke.parser.InvalidTaskException;
import dev.jingyen.duke.parser.TaskParser;
import dev.jingyen.duke.storage.Storage;

public class Duke {
    private static final String SAVE_FILE_PATH =
            System.getProperty("user.home") + File.separator + ".duke" + File.separator + "tasks.txt";

    private final Storage storage;
    private final Ui ui;
    private final TaskList tasks;

    /**
     * A Constructor for dev.jingyen.duke.Duke that initializes the save file path to the default
     * <code>.duke/tasks.txt</code> in the user's home directory, depending on their Operating System.
     */
    public Duke() {
        this(SAVE_FILE_PATH);
    }

    /**
     * A Constructor for dev.jingyen.duke.Duke that sets the save file path to a given path.
     *
     * @param filePath the path to the saved tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();
        try {
            tasks.addAllTasks(storage.load());
        } catch (IOException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Initializes an instance of dev.jingyen.duke.Duke, and runs it.
     *
     * @param args The command line args passed to the program
     */
    public static void main(String[] args) {
        new Duke(SAVE_FILE_PATH).run();
    }

    /**
     * Executes an instance of the Duke chatbot. While the user does not input a goodbye command, Duke interprets
     * different commands passed to it and performs different actions, including but not limited to the following:
     * - store a task to be done
     * - mark a task as done
     * - list the tasks to be done
     * - find tasks that match a search term
     * - remove a task from the list of tasks
     */
    private void run() {
        try (Scanner scanner = new Scanner(System.in)) {
            ui.displayGreeting();

            if (storage.hasSavedTasks()) {
                ui.displayGreetingReminder(tasks.tasksCount());
            }

            System.out.println();
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String output = handleCommand(input);

                if (output.equalsIgnoreCase(Ui.GOODBYE_MESSAGE)) {
                    storage.saveTasks(tasks.getTasks());
                    break;
                }
            }
        } catch (IOException e) {
            ui.displayMessages(e.getMessage());
        }
    }

    /**
     * Interprets a command that was passed to Duke, and returns a corresponding response based on it. If the command or
     * its arguments were malformed, return an error response to the user.
     *
     * @param input The String containing the command, as well as its arguments
     * @return dev.jingyen.duke.Duke's response to the input string
     */
    private String handleCommand(String input) {
        try {
            String[] tokens = input.split(" ");
            Command command = Command.valueOf(tokens[0].toUpperCase());
            switch (command) {
            case HELP:
                return ui.displayHelp();
            case LIST: // show tasks available
                return ui.displayTasks(tasks.getTasks());
            case FIND:
                String term = input.substring("find".length()).strip();
                List<Task> matchingTasks = tasks.searchTasks(term);
                return ui.displayMatchingTasks(matchingTasks);
            case DONE: {
                if (tokens.length < 2) {
                    throw new InvalidInputException("Um, you need to tell me what it is you've done.");
                }
                int index = Integer.parseInt(tokens[1]) - 1;
                Task task = tasks.getTask(index);
                task.markDone();
                return ui.displayMessages(
                        "Okay. So you've done:",
                        task.toString());
            }
            case DELETE: {
                int index = Integer.parseInt(tokens[1]) - 1;
                Task task = tasks.getTask(index);
                tasks.deleteTask(index);
                return ui.displayMessages(
                        "Right, you no longer want me to track:",
                        task.toString(),
                        ui.getTasksLeftMessage(tasks.tasksCount()));
            }
            case TODO:
                // Fallthrough
            case DEADLINE:
                // Fallthrough
            case EVENT: // it's a new task
                return addTask(command, input);
            case BYE: {
                storage.saveTasks(tasks.getTasks());
                return ui.displayGoodbye();
            }
            default:
                return ui.displayMessages("Um, I don't get what you're saying.");
            }
        } catch (DateTimeParseException e) {
            return ui.displayMessages("Um, gimme a valid date.");
        } catch (IllegalArgumentException e) {
            return ui.displayMessages("Um, I don't get what you're saying.");
        } catch (InvalidInputException | IOException e) {
            return ui.displayMessages(e.getMessage());
        }
    }

    private String addTask(Command command, String input) throws InvalidTaskException, DateTimeParseException {
        Task task = TaskParser.parseInput(command, input);
        tasks.addTask(task);
        return ui.displayMessages(
                "Okay, you want to:",
                task.toString(),
                ui.getTasksLeftMessage(tasks.tasksCount()));
    }

    /**
     * Returns dev.jingyen.duke.Duke's response String to a user's input.
     */
    public String getResponse(String input) {
        return handleCommand(input);
    }
}
