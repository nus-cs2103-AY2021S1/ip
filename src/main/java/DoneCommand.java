package main.java;

public class DoneCommand extends Command{
    private int taskIndex;

    public DoneCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (this.taskIndex < 0 || this.taskIndex > tasks.size() - 1) {
            throw new DukeException("\tThere is no such task.");
        }
        Task toChange = tasks.get(this.taskIndex);
        toChange.markAsDone();
        storage.overwrite(tasks);
        String output = "\t Nice! I've marked this task as done:\n" +
                "\t  " + toChange + "\n";
        ui.showMessage(output);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
