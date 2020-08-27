public class DeleteCommand implements Command {
    private boolean isDone;
    private int taskIdToDelete;

    DeleteCommand(int taskIdToDelete) {
        this.isDone = false;
        this.taskIdToDelete = taskIdToDelete;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        if (isDone) {
            throw new CartonaException("Error: DeleteCommand already executed");
        }
        Task deletedTask = taskList.getTask(taskIdToDelete);

        taskList.deleteTask(taskIdToDelete);
        ui.printTaskDeletionMessage(deletedTask, taskList.getSize());
        storage.saveListToFile(taskList);

        this.isDone = true;
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}
