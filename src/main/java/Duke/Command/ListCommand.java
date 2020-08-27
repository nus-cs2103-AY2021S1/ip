package Duke.Command;

import Duke.*;

public class ListCommand extends Command {
    @Override
    public boolean isExited() {
        return false;
    }

    @Override
    public void execute(TaskList tasklist, Ui ui, Storage storage) {
        ui.show(tasklist.toString());
    }
}
