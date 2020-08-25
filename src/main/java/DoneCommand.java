import java.io.IOException;

public class DoneCommand extends Command {
    private short id;

    public DoneCommand(short id) {
        this.id = id;
    }

    @Override public void execute(TaskList tasks, Ui ui, Storage storage)
            throws IOException, IndexOutOfBoundsException {
        Task curr = tasks.getTaskAtIndex(id);
        if (curr.isDone()) {
            ui.displayOutput(Ui.MESSAGE_ALR_DONE);
            return;
        }
        tasks.markAsDone(id);
        ui.showDone(curr);
        storage.updateMemory(tasks.getList());
    }
}
