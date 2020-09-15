import java.io.IOException;

public class ExitCommand extends Command{

    /**
     * Execute the task.
     * @param tasks
     * @param ui
     * @param storage
     * @return a string of response message.
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return ui.exit();
    }

    /**
     * Check if the command is an exit command.
     * @return true.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
