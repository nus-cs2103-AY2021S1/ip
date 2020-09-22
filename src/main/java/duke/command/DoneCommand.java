package duke.command;

import duke.exception.DukeException;
import duke.logic.Storage;
import duke.logic.Ui;
import duke.task.TaskList;


public class DoneCommand implements Command {
    private int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks) throws DukeException {
        // do tasklist stuff here
        tasks.markAsDone(index);
        // Do storage stuff
        Storage.save(tasks);
        // do UI stuff here
        return Ui.printDone("     " + tasks.getTask(index).toString() + "\n");
    }

    @Override
    public boolean setIsFinished() {
        return false;
    }
}
