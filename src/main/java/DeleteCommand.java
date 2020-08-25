import java.io.IOException;

public class DeleteCommand extends Command {
    private short id;

    public DeleteCommand(short id) {
        this.id = id;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws IOException, IndexOutOfBoundsException {
        ui.showDelete((short) (tasks.size() - 1), tasks.getTaskAtIndex(id));
        tasks.delete(id);
        storage.updateMemory(tasks.getList());
    }
}
