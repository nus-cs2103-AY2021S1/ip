package main.java;

public class DeleteCommand extends Command{
    private int taskIndex;

    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskIndex < 0 || this.taskIndex > tasks.size() - 1) {
            throw new DukeException("\tThere is no such task.");
        }
        Task toDelete = tasks.get(this.taskIndex);
        tasks.remove(this.taskIndex);
        storage.overwrite(tasks);
        String output = "\t Noted. I've removed this task:\n" +
                "\t   " + toDelete + "\n" +
                "\t Now you have " + tasks.size() + " tasks in the list.\n";
        ui.showMessage(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
