public class ExitCommand extends Command {

    void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ui.exit();
    }

    boolean isExit() {
        return true;
    }
}
