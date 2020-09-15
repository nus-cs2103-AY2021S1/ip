import java.io.IOException;

public class HelpCommand extends Command {

    /**
     * Execute the command.
     * @param tasks
     * @param ui
     * @param storage
     * @return
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return "not helpful";
    }

    /**
     * Check if the command is an exit command
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
