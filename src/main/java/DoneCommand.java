public class DoneCommand extends Command {
    private Task task;
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Storage storage, TaskList taskList, Ui ui) throws InvalidInputException {
        Task task = taskList.getTask(index);
        taskList.setAsDone(index);
        storage.write(taskList.getListOfTasks());
        ui.displayDone(task);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}

