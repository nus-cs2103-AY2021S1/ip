public class DeadlineCommand extends Command {
    boolean exitCheck;
    Task deadlineTask;

    public DeadlineCommand(String description, String deadlineDate, String deadlineTime) {
        this.deadlineTask = new Deadline(description, deadlineDate, deadlineTime);
    }

    public void runCommand(TaskList arrayOfTasks, Ui ui, Storage storage) {
        ui.addedMessage(deadlineTask);
        arrayOfTasks.addTask(deadlineTask);
        ui.printTaskCount();
        storage.changeFile();
    }

    public boolean exitCheck() {
        exitCheck = false;
        return exitCheck;
    }
}