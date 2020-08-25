package taskbot.main;

import taskbot.command.Command;
import taskbot.parser.Parser;
import taskbot.exceptions.TaskbotException;
import taskbot.storage.Storage;
import taskbot.ui.Ui;

import taskbot.task.TaskList;

/**
 * This is the main driver class.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize TaskBot's UI
        Ui ui = new Ui();

        // Prints the title to the console
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
