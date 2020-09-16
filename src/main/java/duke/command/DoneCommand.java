package duke.command;

public class DoneCommand extends Command {
    private int idx;

    public DoneCommand(int idx) {
        this.idx = idx - 1;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            if (tasks.size() == 0) {
                throw new IndexOutOfBoundsException();
            }
            Task doneTask = tasks.get(idx);
            doneTask.markDone();
            ui.showDoneMessage(doneTask);
            storage.writeFile(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("The number does not exist!");
        }
    }

    public boolean isExit() {
        return false;
    }
}
