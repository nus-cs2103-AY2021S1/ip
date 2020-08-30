import java.io.IOException;

public class DeleteCommand extends Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        Task deletedTask = taskList.remove(index);
        ui.printDeleteMessage(deletedTask, taskList);
        super.execute(taskList, ui, storage);
    }
}
