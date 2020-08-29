public class ExitCommand extends Command {

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ui.printExit();
        storage.save(tasks.getTasks());
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
