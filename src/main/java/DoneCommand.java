public class DoneCommand extends Command {
    int idx;

    DoneCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Task task = tasks.markAsDone(idx);
        ui.showTaskDone(task);
        storage.saveTasksToFile(tasks);
    }
}
