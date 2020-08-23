import Exceptions.DukeException;

public class ListCommand extends Command {
    ListCommand(String string) {
        super(string);
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.execute(tasks, ui, storage);
        Task.listing();
    }

}
