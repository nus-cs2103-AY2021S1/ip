import java.io.IOException;

public class ExitCommand extends Command {
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException, DukeException {
        isExit = true;
        ui.printExitMessage();
        super.execute(taskList, ui, storage);
    }
}
