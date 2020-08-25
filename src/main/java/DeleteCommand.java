import java.io.IOException;
import java.util.Objects;

public class DeleteCommand extends Command {
    private final short id;

    public DeleteCommand(short id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, IndexOutOfBoundsException {
        ui.showDelete((short) (tasks.size() - 1), tasks.getTaskAtIndex(id));
        tasks.delete(id);
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
        DeleteCommand that = (DeleteCommand) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
