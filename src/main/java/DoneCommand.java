public class DoneCommand extends Command {
    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui){
        try {
            int taskNumber = ui.readTaskNumber();
            if (taskList.size() >= taskNumber && taskNumber > 0) {
                Task task = taskList.getTask(taskNumber);
                task.markAsDone();
                storage.updateData(task.store(), taskNumber);
                ui.done(task);
            } else {
                throw new DukeException("Oops! No such task!");
            }
        } catch (DukeException e) {
            ui.showError(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
