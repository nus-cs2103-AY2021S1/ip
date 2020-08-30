package chatterbox;

import java.io.IOException;

import chatterbox.task.Task;
import chatterbox.task.TaskList;

/**
 * Handles the main loop and the high level logic flow of the program.
 */
public class Chatterbox {
    private final TaskList tasks;

    /**
     * Initializes the task list and load stored tasks.
     */
    public Chatterbox() {
        Storage store = new Storage();
        tasks = new TaskList(store);
    }

    /**
     * Loads stored tasks.
     * @throws IOException
     */
    public void loadTasks() throws ChatterboxException, IOException {
        tasks.loadTasks();
    }

    /**
     * Shows the welcome message, then processes each line of user input until "bye" is typed.
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
        if (input.strip().equals("")) {
            throw new ChatterboxException("Input cannot be empty.");
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
                throw new ChatterboxException("Please enter a number after the command.");
            }

            // Mark as done or delete based on the command
            if (command.equals("done")) {
                return tasks.setTaskAsDone(taskNo);
            } else {
                return tasks.deleteTask(taskNo);
            }
        } else if (command.equals("find")) {
            return tasks.findTasks(input.split(" ", 2)[1]);
        } else if (command.equals("deadline") || command.equals("todo") || command.equals("event")) {
            Task t = Parser.parseTask(input);
            return tasks.addTask(t);
        } else {
            throw new ChatterboxException("That's not a valid command.");
        }
    }
}

