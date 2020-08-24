package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

public class FindCommand extends Command {

    String word;

    public FindCommand(String word) {
        this.word = word;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String result = tasks.printTasksWithKeyword(word);
        ui.printMessage(result);
    }
}
