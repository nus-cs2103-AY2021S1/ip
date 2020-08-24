public class ByeCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.save(tasks);
            ui.end();
        } catch (DukeException e) {
            ui.say(e.getMessage());
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
