public class AddCommand implements Command {
    private boolean isDone;
    private Task taskToAdd;

    AddCommand(Task taskToAdd) {
        this.isDone = false;
        this.taskToAdd = taskToAdd;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws CartonaException {
        if (isDone) {
            throw new CartonaException("Error: AddCommand already executed");
        }

        taskList.addTask(taskToAdd);
        ui.printTaskAddingMessage(taskToAdd, taskList.getSize());
        this.isDone = true;

        storage.saveListToFile(taskList);
    }

    @Override
    public boolean isExitCmd() {
        return false;
    }
}
