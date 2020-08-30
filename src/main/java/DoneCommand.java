import java.io.IOException;

public class DoneCommand extends Command {
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        Task doneTask = taskList.setDoneTask(index);
        ui.printDoneMessage(doneTask);
        super.execute(taskList, ui, storage);
    }

}
