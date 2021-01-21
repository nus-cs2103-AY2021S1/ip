package duke;

import java.io.IOException;

import duke.command.Command;
import duke.exception.DateException;
import duke.exception.DukeException;
import duke.exception.MissingInformationException;

/**
 * Represents a task managing system.
 * It makes use of different components to display UI,
 * keep track of tasks and manage the storage of data.
 */
public class Duke {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Creates an instance of Duke.
     * It initialises the UI, creates a storage based on the filepath
     * and initialises the existing tasks.
     * @param filePath The location where the task data is stored
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException | DateException e) {
            e.printStackTrace();
            tasks = new TaskList();
        }
    }



    /**
     * Runs the task management program.
     * It allows the user to input commands and executes
     * the commands accordingly, until the user chooses to
     * exit the program.
     */
    public void run() {
        System.out.println(ui.displayWelcome());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                String response = c.execute(tasks, ui, storage);
                ui.printMessage(response);
                isExit = c.isExit();
            } catch (DukeException | MissingInformationException | DateException e) {
                ui.printMessage(e.getMessage());
            } catch (IndexOutOfBoundsException | NumberFormatException e) {
                ui.printMessage("Invalid task number!");
            }
        }
        ui.close();
    }

    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);

        } catch (DukeException | MissingInformationException | DateException e) {
            return e.getMessage();
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            return "Invalid task number!";
        }
    }

    /**
     * Allows user to start running the program.
     * @param args Command line arguments which are not used for this program
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

}
