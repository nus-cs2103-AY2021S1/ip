public class ListCommand extends Command {

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String text = tasks.listContents();
            ui.say(text);
        } catch (DukeException e) {
            throw(e);
        }
    }
}
