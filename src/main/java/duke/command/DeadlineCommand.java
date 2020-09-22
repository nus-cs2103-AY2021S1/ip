package duke.command;

import duke.exception.DukeException;
import duke.logic.Storage;
import duke.logic.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeadlineCommand implements Command {
    private Task toBeAdded;

    public DeadlineCommand(Task toBeAdded) {
        this.toBeAdded = toBeAdded;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        // Do TaskList stuff
        tasks.add(toBeAdded);
        // Do storage stuff
        Storage.save(tasks);
        // Do UI stuff
        return Ui.printAdd(toBeAdded.toString() + "\n", tasks.length());
    }
}
