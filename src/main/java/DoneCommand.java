public class DoneCommand extends Command {
    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showTaskDone(tasks.markTaskDone(index - 1));
        storage.saveTaskList(tasks);
    }
}
