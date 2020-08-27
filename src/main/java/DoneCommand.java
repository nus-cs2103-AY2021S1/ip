public class DoneCommand implements Command{
    private boolean isDone;
    private int taskIdToComplete;

    DoneCommand(int taskIdToComplete) {
        this.isDone = false;
        this.taskIdToComplete = taskIdToComplete;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        if (isDone) {
            throw new CartonaException("Error: DoneCommand already executed");
        }

        taskList.completeTask(taskIdToComplete);
        Task completedTask = taskList.getTask(taskIdToComplete);
        ui.printTaskDoneMessage(completedTask);

        storage.saveListToFile(taskList);
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}
