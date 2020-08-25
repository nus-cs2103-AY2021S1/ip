public class ListCommand extends Command {
    public ListCommand() {
        super("list");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.listTasks();
        // ui.showListMessage();
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
