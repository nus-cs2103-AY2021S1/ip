import java.io.IOException;

public class ListCommand extends Command {

    /**
     * Generate a list of tasks to user.
     * @param tasks
     * @param ui
     * @param storage
     * @return a String to reply user.
     * @throws IOException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        return ui.generateList(tasks);
    }

    /**
     * To show if the programme can end.
     * @return a boolean
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
