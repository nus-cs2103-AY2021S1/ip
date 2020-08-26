public class DeleteCommand extends Command {
    int taskIdx;

    DeleteCommand(int taskIdx) {
        this.taskIdx = taskIdx;

    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        Task task = tasks.deleteTask(taskIdx);
        ui.showDeleteTask(task, tasks.getSize());
        storage.saveTasksToFile(tasks);
    }

}
