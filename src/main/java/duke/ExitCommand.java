package duke;

public class ExitCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        isExit = true;
        ui.printGoodbye();
        super.execute(tasks, ui, storage);
    }
}
