package Duke.Commands;

import Duke.Errors.DukeException;
import Duke.Errors.WrongInputException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
public class RandomCommand extends Command {
    public RandomCommand(String string) {
        super(string);
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        super.execute(tasks, ui, storage);
        throw new WrongInputException();
    }
}
