package alice;

import alice.command.Command;
import alice.command.InvalidCommandException;
import alice.command.result.CommandResult;
import alice.command.result.InvalidCommandResult;
import alice.storage.SaveStatus;
import alice.storage.StorageFile;
import alice.task.TaskList;
import alice.ui.cli.Cli;
import alice.util.Parser;

/**
 * Represents the ALICE program.
 */
public class Alice {
    private TaskList tasks;
    private final StorageFile storageFile;

    private Cli cli;

    /**
     * Creates an ALICE program with default save file location.
     * If the file does not exist, creates the appropriate directory and file.
     */
    public Alice() {
        this(StorageFile.DEFAULT_FILE_PATH);
    }

    /**
     * Creates an ALICE program that reads data from specified location.
     * If the file does not exist, creates the appropriate directory and file.
     *
     * @param filePath relative path to the data file.
     */
    public Alice(String filePath) {
        storageFile = new StorageFile(filePath);
        try {
            // Read stored data
            tasks = new TaskList(storageFile.load());
        } catch (AliceException ex) {
            // Cannot load, create or read data file.
            // Starts with a new file.
            tasks = new TaskList();
        }
    }

    /**
     * Returns the status of loading the save file from specified or default location.
     *
     * @return the load status of the file.
     */
    public String getLoadStatus() {
        return storageFile.getLoadStatus();
    }

    /**
     * Processes the command provided by the user.
     *
     * @param inputCommand the command string input by the user
     * @return the CommandResult which contains instructions for replying the user.
     */
    public CommandResult processCommand(String inputCommand) {
        try {
            Command c = Parser.parseCommand(inputCommand);
            return c.process(tasks, storageFile);
        } catch (InvalidCommandException ex) {
            return new InvalidCommandResult(ex.getMessage());
        }
    }

    /**
     * Initialises and prepares the Alice's Command Line Interface.
     *
     * @return the Alice object with CLI initialised.
     */
    protected Alice initialiseCli() {
        this.cli = new Cli();
        return this;
    }

    /**
     * Runs the Alice program on the command line.
     * The program uses the read-eval-print-loop framework.
     */
    protected void run() {
        cli.displayInitMessage(getLoadStatus());
        cli.displayWelcomeMsg();

        cli.displayLine();
        boolean isExit = false;

        while (!isExit) {
            String input = cli.readUserInput();
            cli.displayLine();
            CommandResult result = processCommand(input);

            // Prints result on CLI
            if (result.isCommandFailure()) {
                cli.displayError(result.getMessage());
            } else {
                cli.displayOutput(result.getMessage());
            }

            if (result.getSaveStatus() == SaveStatus.SAVE_FAILED) {
                cli.displayError("Failed to save data");
            }
            isExit = result.shouldExit();

            cli.displayLine();
        }
    }
}
