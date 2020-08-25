package duke.command;

import duke.exception.DukeException;
import duke.exception.EmptyFindException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {

    private String input;

    public FindCommand(String input) {
        this.input = input;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        if (input.isEmpty()) {
            throw new EmptyFindException();
        } else {
            TaskList taskList = tasks.matchAll(input);
            if (taskList.isEmpty()) {
                ui.emptyFind(input);
            } else {
                ui.showTaskList(taskList, "matching");
            }
        }
    }
}
