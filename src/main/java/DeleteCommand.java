public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.showTaskDeleted(tasks.deleteTask(index - 1), tasks.size());
        storage.saveTaskList(tasks);
    }
}
