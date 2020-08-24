public class DeleteCommand extends Command {
    int taskNumber;
    protected DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task task = tasks.deleteTaskFromList(taskNumber);
            ui.printDeletedTask(task, tasks.size());
            storage.writeToFile(tasks.getTasks());
        } catch (DukeException e) {
            throw e;
        }
    }
}
