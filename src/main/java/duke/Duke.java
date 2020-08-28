package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Implements the chatbot application
 * 
 * @author Audrey Felicio Anwar
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    
    public Duke(String filePath) {
        try {
            this.ui = new Ui();
            this.storage = new Storage(filePath);
            this.tasks = new TaskList(storage.loadTasks());
        } catch (DukeException error) {
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the main logic of chat bot.
     */
    public void run() {
        ui.greetUser();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.readInput();
                ui.showLine();
                Command command = Parser.parse(input);
                if (command != null) {
                    command.executeCommand(tasks, ui, storage);
                    isExit = command.isExit();
                }
            } catch (DukeException error) {
                ui.printMessage(error.getMessage());
            } finally {
                ui.showLine();
            }
        }
        try {
            storage.saveTasks(tasks.getTasks());
        } catch (DukeException error) {
            ui.printMessage(error.getMessage());
        }
    }

    /**
     * Initializes and runs the program.
     * 
     * @param args Command line inputs.
     */
    public static void main(String[] args) {
       new Duke("./tasks.txt").run();
    }
}
