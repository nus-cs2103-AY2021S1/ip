package duke;

import duke.command.Command;
import duke.exception.DukeInputException;
import duke.exception.DukeSaveDataException;
import duke.io.InputHandler;
import duke.io.OutputHandler;

import java.nio.file.Path;

public class Duke {

    private TaskManager taskManager;
    private Ui ui;
    private Path filePath;
    private SaveManager saveManager;

    public Duke() {
        this(Path.of("/data", "data.txt"));
    }

    public Duke(Path filePath) {
        // Initialise properties
        this.filePath = filePath;
        this.ui = new Ui(new InputHandler(), new OutputHandler());
        this.saveManager = new SaveManager(this.filePath);

        // Attempts to load save file.
        // If fails, initialises Duke without save data.
        try {
            this.taskManager = saveManager.load();
        } catch (DukeSaveDataException e) {
            this.ui.displayException(e);
            this.taskManager = new TaskManager();
        }

    }

    public void run() {

        // Main software loop.
        while(true) {

            try {
                // Parse user input into software command.
                Command command = Parser.parse(this.ui.readCommand());

                // Execute user command.
                command.execute(this.ui, this.taskManager, this.saveManager);

                // Terminate software loop if exit command is given.
                if (command.isByeCommand()) {
                    break;
                }

            } catch (DukeInputException e) {
                // Display Exception without terminating loop if one is thrown.
                ui.displayException(e);
            }

        }

    }

    public static void main(String[] args) {
        // Initialize Duke with save data and send welcome message
        Duke duke = new Duke(Path.of("data/data.txt"));
        duke.ui.displayGreet();

        // Start input loop
        duke.run();

    }
}
