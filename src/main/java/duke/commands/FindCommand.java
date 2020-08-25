package duke.commands;

import duke.tasks.Task;
import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasks.TaskList;

public class FindCommand extends Command {
    private String description;

    public FindCommand(String description) {
        this.description = description;
    }

    public void execute(TaskList tasklist, Ui ui, Storage storage) throws DukeException {
        boolean wordExist = false;
        for (Task i : tasklist.getList()) {
            String task = i.toString();
            if (task.contains(description)) {
                ui.showMessage(task);
                wordExist = true;
            }
        }
        if (!wordExist) {
            throw new DukeException("word does not exist in TaskList!");
        }
    }
}
