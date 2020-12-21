package duke;

import java.io.IOException;

/**
 * Represents a Duke class.
 */
public class Duke {

    /** File path */
    private static final String FILEPATH = "data/duke.txt";

    /** Storage for reading and writing all tasks */
    private Storage storage;

    /** Task list containing tasks */
    private TaskList tasks;

    /** Handles printing of user interaction */
    private final Ui ui = new Ui();

    /**
     * Constructs Duke and initialises storage and tasks.
     */
    public Duke() {
        try {
            storage = new Storage(FILEPATH);
            tasks = new TaskList(storage.readFromFile());
        } catch (DukeException | IOException ex) {
            ui.showLoadingError();
            tasks = new TaskList(tasks.getTasks());
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.displayGreetings();
        while (ui.hasMoreInput()) {
            try {
                String userInput = ui.readCommand();
                Command command = Parser.parseCommands(userInput);
                command.execute(this.tasks, this.storage, this.ui);
            } catch (DukeException | IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public Ui getUi() {
        return this.ui;
    }

    /**
     * Executes the main program.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    String getResponse(String input) {
        try {
            Command command = Parser.parseCommands(input);
            if (input.equals("bye")) {
                System.exit(0);
            }
            return command.execute(this.tasks, this.storage, this.ui);
        } catch (IOException | DukeException e) {
            return e.getMessage();
        }
    }
}




