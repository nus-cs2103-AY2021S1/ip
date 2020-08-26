import java.io.IOException;

public class DoneCommand extends Command{
    private final int num;

    public DoneCommand(String command, int num) {
        super(command, false);
        this.num = num;
    }

    protected void execute(TaskList list, Ui ui, Storage storage) {
        try {
            if (num <= list.size()) {
                Task current = list.get(num - 1);
                current.markAsDone();
                ui.doneMessage(current.isDone, current.description);
                storage.editFile(num - 1);
            } else {
                throw new DukeException("☹ OOPS!!! there is no such task");
            }
        } catch (DukeException | IOException e) {
            ui.errorEncounter(e);
        }
    }
}
