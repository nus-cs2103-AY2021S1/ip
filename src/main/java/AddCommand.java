public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException{
        taskList.addTask(task);
        storage.write(taskList.getListOfTasks());
        ui.displayAddition(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
