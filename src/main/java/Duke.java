/**
 * Responsible for running the Duke application, and responding to user.
 * Initializes and terminates the application.
 */
public class Duke {
    private TaskList tasks;
    private final Storage storage;
    private String storageErrorMessage;
    private boolean hasStorageError;

    /**
     * Constructor.
     */
    public Duke() {
        this.storage = new Storage();
        try {
            this.tasks = storage.loadFile();
            this.hasStorageError = false;
        } catch (Exception e) {
            System.err.println(e);
            this.tasks = new TaskList();
            this.storageErrorMessage = e.toString();
            this.hasStorageError = true;
        }
    }

    public String getStorageErrorMessage() {
        return storageErrorMessage;
    }

    public boolean hasStorageError() {
        return hasStorageError;
    }

    protected CommandResult acceptInput(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(this.tasks, this.storage);
        } catch (DukeException e) {
            System.err.println(e);
            return new CommandResult(e.getMessage());
        }
    }
}
