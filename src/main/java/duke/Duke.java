package duke;

import java.nio.file.Path;

import duke.command.Command;
import duke.command.RemindCommand;
import duke.exception.DukeInputException;
import duke.exception.DukeSaveDataException;
import duke.io.InputHandler;
import duke.io.OutputHandler;



/** Contains main software logic loop and initialization logic.*/
public class Duke {

    /** TaskManager object that contains all the tasks in Duke's memory */
    private TaskManager taskManager;

    /** Ui object that handles all display and input from user */
    private Ui ui;

    /** Path object that contains the relative directory path to the save file */
    private Path filePath;

    /** SaveManager object that handles all saving and loading of save data on load or on exit */
    private SaveManager saveManager;

    /**
     * Constructs new Duke object with default save file directory and default <code>OutputHandler</code>.
     * Defaults save file directory to "/data/data.txt".
     * Defaults <code>OutputHandler</code> displays to <code>System.out</code>.
     * Attempts to load the save file from the default directory.
     */
    public Duke() {
        this(Path.of("/data", "data.txt"));
    }

    /**
     * Constructs new Duke object with given save file directory and default <code>OutputHandler</code>.
     * Default <code>OutputHandler</code> displays to <code>System.out</code>.
     * Attempts to load the save file from the given directory.
     *
     * @param filePath Save file directory.
     */
    public Duke(Path filePath) {
        this(filePath, new OutputHandler());
    }

    /**
     * Constructs new Duke object with custom <code>OutputHandler</code> and default save file directory.
     * Defaults save file directory to "/data/data.txt".
     * Attempts to load the save file from the default directory.
     *
     * @param outputHandler Custom <code>OutputHandler</code>.
     */
    public Duke(OutputHandler outputHandler) {
        this(Path.of("/data", "data.txt"), outputHandler);
    }

    /**
     * Constructs new Duke object with custom save file directory and custom <code>OutputHandler</code>.
     * Attempts to load the save file from the given directory.
     *
     * @param filePath Path object representing save file directory.
     * @param outputHandler Custom <code>OutputHandler</code>.
     */
    public Duke(Path filePath, OutputHandler outputHandler) {
        // Initialise properties
        this.filePath = filePath;
        this.ui = new Ui(new InputHandler(), outputHandler);
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

    /**
     * Activates main software loop, accepting and processing commands.
     * Parser parses inputs to commands which are then executed by Duke.
     * Exceptions trigger an error message for users without ending the program.
     * Main loop only ends on parsing a "bye" command from user.
     * If unable to save, Duke will exit without saving.
     */
    public void run() {

        while (true) {

            try {
                Command command = Parser.parse(this.ui.readCommand());
                command.execute(this.ui, this.taskManager, this.saveManager);
                this.ui.displayQueuedMessages();

                // Terminate loop on Bye Command
                if (command.isByeCommand()) {
                    break;
                }

                assert !command.isByeCommand();

            } catch (DukeInputException e) {
                ui.displayException(e);
            }

        }

    }

    /**
     * Executes a command parsed from a given user input.
     * This method is meant to be used with the GUI version of Duke where input and outputs are handled differently.
     *
     * @param userInput Given user input processed by other modules.
     * @return Boolean representing if the command has been parsed as a "bye" command.
     */
    public boolean processOneCommand(String userInput) {

        try {
            // Parse user input
            Command command = Parser.parse(userInput);

            // Execute user command
            command.execute(this.ui, this.taskManager, this.saveManager);

            // Send messages from app
            this.ui.displayQueuedMessages();

            return command.isByeCommand();

        } catch (DukeInputException e) {
            ui.displayException(e);
            return false;
        }

    }

    /**
     * Runs through processes to be done after loading data.
     * Asks UI to send greeting to user, then reminds them of deadlines due today.
     */
    public void initialize() {
        this.ui.displayGreet();

        new RemindCommand().execute(this.ui, this.taskManager, this.saveManager);
        this.ui.displayQueuedMessages();
    }

    /**
     * Initializes the CLI version of the app.
     *
     * @param args CLI arguments. None required.
     */
    public static void main(String[] args) {
        Duke duke = new Duke(Path.of("data/data.txt"));
        duke.initialize();

        duke.run();

    }
}
