import java.io.IOException;
import java.util.Objects;

public class DoneCommand extends Command {
    private final short id;

    public DoneCommand(short id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        DoneCommand that = (DoneCommand) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
