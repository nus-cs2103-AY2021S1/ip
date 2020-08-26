package duke.command;

import duke.exception.DukeException;
import duke.utility.Storage;
import duke.utility.TaskList;
import duke.utility.Ui;

public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String message = ui.findTask(keyword, tasks);
        ui.sendMessage(message);
    }
}
