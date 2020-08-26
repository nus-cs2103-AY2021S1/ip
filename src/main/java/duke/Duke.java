package duke;

import java.io.IOException;

/**
 * Represents a Duke class.
 */
public class Duke {

    /** Storage for reading and writing all tasks */
    private Storage storage;

    /** Task list containing tasks */
    private TaskList tasks;

    /** Handles printing of user interaction */
    private final Ui ui;

    /**
     * Constructs a new instance of Duke object.
     *
     * @param filePath Path of file to store tasks.
     */
    public Duke(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.read());
        } catch (DukeException | IOException ex) {
            ui.showLoadingError();
            tasks = new TaskList(tasks.getTasks());
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.printGreetings();
        while (ui.hasMoreInput()) {
           try {
               String userInput = ui.readCommand();
               Command command = Parser.parseCommands(userInput);
               command.execute(this.tasks, this.storage, this.ui);
           } catch (DukeException | IOException ex) {
               System.out.println(ex.getMessage());
           } finally {
               System.out.println(Ui.getLine());
           }
        }
    }

    /**
     * Executes the main program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}




