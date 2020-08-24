import java.io.IOException;

public class DoneCommand implements Command {
    private final int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.markDone(index);
            storage.save(tasks);
            ui.showMarkDone(tasks.get(index));
        } catch (DukeInvalidIndexException e) {
            ui.showError(e);
        } catch (IOException e) {
            ui.showError(e);
        }
        return true;
    }
}
