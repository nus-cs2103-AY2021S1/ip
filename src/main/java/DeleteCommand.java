public class DeleteCommand extends Command {

    @Override
    public void execute(TaskList taskList, Storage storage, Ui ui) {
        try {
            int taskNumber = ui.readTaskNumber();
            if (taskList.size() >= taskNumber && taskNumber > 0) {
                Task task = taskList.removeTask(taskNumber);
                storage.updateData("", taskNumber);
                ui.deleteTask(task, taskList);
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
