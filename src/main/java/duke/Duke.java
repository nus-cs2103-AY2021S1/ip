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

    /**
     * Initializes a Duke object.
     */
    public Duke() {
        this("./tasks.txt");
    }

    /**
     * Initializes a Duke object
     *
     * @param filePath The location of saved data.
     */
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
                ui.printMessage(ui.showLine());
                Command command = Parser.parse(input);
                if (command != null) {
                    ui.printMessage(command.executeCommand(tasks, ui, storage));
                    isExit = command.isExit();
                }
            } catch (DukeException error) {
                ui.printMessage(error.getMessage());
            } finally {
                ui.printMessage(ui.showLine());
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

    /**
     * Returns a response to be shown to the user.
     *
     * @param input Input from the user.
     * @return Response to the user.
     */
    public String getResponse(String input) {
        StringBuilder response = new StringBuilder();
        try {
            response.append(ui.showLine() + "\n");
            Command command = Parser.parse(input);
            if (command != null) {
                response.append(command.executeCommand(tasks, ui, storage) + "\n");
            }
        } catch (DukeException error) {
            response.append(error.getMessage() + "\n");
        } finally {
            response.append(ui.showLine() + "\n");
        }
        return response.toString();
    }

    /**
     * Shows welcome message to user.
     *
     * @return Welcome message to user.
     */
    public String getWelcomeMessage() {
        return ui.getGreetings();
    }
}
