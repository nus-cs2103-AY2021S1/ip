package taskbot.main;

import taskbot.command.Command;
import taskbot.exceptions.TaskbotException;
import taskbot.parser.Parser;
import taskbot.storage.Storage;
import taskbot.task.TaskList;
import taskbot.ui.Ui;

/**
 * This is the main driver class.
 */
public class Main {
    /**
     * Initialises the TaskBot UI and takes in commands from the user.
     * @param args Unused args.
     */
    public static void main(String[] args) {
        // Initialise Taskbot & required classes
        Ui ui = new Ui();

        // Prints the title to the console and greets the user
        ui.printTitle();

        // Initialize database and task list
        Storage storage = new Storage(System.getProperty("user.dir"));
        TaskList taskList = new TaskList(storage);

        // Greets the user
        ui.greet();

        // Exit condition
        boolean isExit = false;

        // Loops while exit condition is false
        while (!isExit) {
            try {
                // Take in user input
                String command = ui.readCommand();
                ui.showLine();

                // Process user input
                Command cmd = Parser.parse(command);
                cmd.execute(taskList, ui);

                // Check if it was an exit command
                isExit = cmd.isExit();
            } catch (TaskbotException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }

        }
    }
}
