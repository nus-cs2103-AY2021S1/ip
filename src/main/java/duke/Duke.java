package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The Duke class implements a Duke chat-bot, called Dino,
 * with functionalities to keep track of tasks.
 *
 * @author Amy Lim Zhi Ting
 * @version v0.1
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs Duke object, initialised with String file path variable
     * that will be used by Storage to load task data stored in hard disk
     * when Duke starts up, or when writing to file due to changes in task data.
     *
     * @param filePath String of file path
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.loadData());
        } catch (DukeException e) {
            ui.showError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    /**
     * Parses user input and executes command.
     * If the command is invalid, DukeException is caught.
     */
    public void run() {
        ui.greet();
        Scanner scanner = new Scanner(System.in);
        boolean isBye = false;

        while (!isBye && scanner.hasNextLine()) {
            String input = scanner.nextLine();
            try {
                Command c = Parser.parse(input);
                c.execute(storage, tasks, ui);
                isBye = c.isBye();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
        scanner.close();
    }

    /**
     * Initialises Duke with the file record of task history, and runs Duke.
     *
     * @param args an array of command-line arguments for the application
     */
    public static void main(String[] args) {
        new Duke("./data/tasklist.txt").run();
    }

    /**
     * Get the response from Dino given the user input.
     *
     * @param input The command input user types in.
     * @return The response from duke command agent.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.executeToString(storage, tasks, ui);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
