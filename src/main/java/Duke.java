/**
 * Responsible for running the Duke application, and responding to user.
 * Initializes and terminates the application.
 */
public class Duke {
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor.
     */
    public Duke() {
        this.storage = new Storage();
        try {
            this.tasks = storage.loadFile();
        } catch (Exception e) {
            System.err.println(e);
            this.tasks = new TaskList();
        }
    }

    protected CommandResult acceptInput(String input) {
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(this.tasks, this.storage);
        } catch (DukeException e) {
            System.err.println(e);
        }

        return new CommandResult("Oops! Something went wrong");
    }
}
