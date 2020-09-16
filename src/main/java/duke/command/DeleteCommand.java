package duke.command;

public class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task deletedTask = tasks.get(idx);
            tasks.delete(idx);
            ui.showDeleteMessage(deletedTask, tasks);
            storage.writeFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The number does not exist!");
        }
    }
    
    public boolean isExit() {
        return false;
    }
}
