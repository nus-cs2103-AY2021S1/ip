public class TodoCommand extends Command {
    protected Task toDoTask;
    protected boolean isExit;

    public TodoCommand(String description) {
        toDoTask = new Todo(description);
    }

    public void runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        ui.addedMessage(toDoTask);
        arrayOfTasks.addTask(toDoTask);
        ui.printTaskCount();
        storage.changeFile();
    }

    public boolean exitCheck() {
        isExit = false;
        return isExit;
    }
}