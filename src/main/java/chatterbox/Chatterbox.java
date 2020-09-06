package chatterbox;

import java.io.IOException;

import chatterbox.task.Task;
import chatterbox.task.TaskList;

/**
 * Handles the main loop and the high level logic flow of the program.
 */
public class Chatterbox {
    private static final String ERROR_EMPTY_INPUT = "Input cannot be empty.";
    private static final String ERROR_NO_TASK_NUMBER = "Please enter a number after the command.";
    private static final String ERROR_INVALID_COMMAND = "That's not a valid command.";

    private final TaskList tasks;

    /**
     * Initializes the task list and load stored tasks.
     */
    public Chatterbox() {
        Storage storage = new Storage();
        tasks = new TaskList(storage);
    }

    /**
     * Loads stored tasks.
     *
     * @throws ChatterboxException  If tasks cannot be loaded.
     * @throws IOException  If tasks cannot be loaded.
     */
    public void loadTasks() throws ChatterboxException, IOException {
        tasks.loadTasks();
    }

    /**
     * Shows the welcome message, then processes each line of user input until "bye" is typed.
     *
     * @param input The user's input string.
     * @return Chatterbox's response to the input.
     */
    public String getResponse(String input) {
        try {
            return processInput(input);
        } catch (ChatterboxException | IOException e) {
            return e.toString();
        }
    }

    /**
     * Processes user input and returns Chatterbox's response.
     *
     * @param input Raw user input.
     * @throws ChatterboxException If input string is empty or invalid command is given.
     * @throws IOException         If data cannot be read/written from the save file.
     */
    private String processInput(String input) throws ChatterboxException, IOException {
        // Check if input is just whitespace
        if (input.equals("")) {
            throw new ChatterboxException(ERROR_EMPTY_INPUT);
        }

        // Get first word of input
        String command = (input + " ").split(" ")[0].toLowerCase();

        // Process command
        if (command.equals("list")) {
            return tasks.getPrintableTaskList();
        } else if (command.equals("done") || command.equals("delete")) {
            // Get the task number after the command, check if it is valid
            int taskNo;
            try {
                taskNo = Integer.parseInt(input.split(" ")[1]) - 1;
            } catch (NumberFormatException e) {
                throw new ChatterboxException(ERROR_NO_TASK_NUMBER);
            }

            // Mark as done or delete based on the command
            if (command.equals("done")) {
                return tasks.setTaskAsDone(taskNo);
            } else {
                return tasks.deleteTask(taskNo);
            }
        } else if (command.equals("find")) {
            String searchKeyword = input.split(" ", 2)[1];
            return tasks.findTasks(searchKeyword);
        } else if (command.equals("deadline") || command.equals("todo") || command.equals("event")) {
            Task t = Parser.parseTask(input);
            return tasks.addTask(t);
        } else if (command.equals("archive")) {
            return tasks.archive();
        } else {
            throw new ChatterboxException(ERROR_INVALID_COMMAND);
        }
    }
}

