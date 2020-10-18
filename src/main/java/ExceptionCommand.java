import java.io.IOException;

public class ExceptionCommand extends Command {
    final Exception e;

    /**
     * Constructor for ExceptionCommand
     * @param e Exception thrown from previously executed command
     * @param tasks Existing list of tasks
     * @param ui User interface to be updated
     * @param storage Storage to be updated
     */
    public ExceptionCommand(Exception e, TaskList tasks, Ui ui, Storage storage) {
        super(tasks, ui, storage);
        this.e = e;
    }

    /**
     * Executes the ExceptionCommand
     * @return Returns String message of exception thrown
     * @throws IOException Exception thrown when updating storage file
     */
    @Override
    public String execute() throws IOException {
        return "Jarvis exception: " + this.e.getMessage();
    }
}
