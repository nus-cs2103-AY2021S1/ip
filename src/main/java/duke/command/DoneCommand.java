package duke.command;

import duke.TaskList;
import duke.Ui;

public class DoneCommand implements Command {
    int index;

    public DoneCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks) {
        // do tasklist stuff here
        tasks.markAsDone(index);
        // do UI stuff here
        return Ui.printDone("     " + tasks.getTask(index).toString() + "\n");
        // do storage stuff here
        // tbc
    }

    @Override
    public boolean setIsFinished() {
        return false;
    }
}
