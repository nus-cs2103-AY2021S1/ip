public class DeleteCommand extends Command {
    private Task task;
    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        Task task = taskList.getTask(index);
        taskList.deleteTask(index);
        storage.write(taskList.getListOfTasks());
        ui.displayDeletion(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

