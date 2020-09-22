package duke.command;

import duke.logic.Ui;
import duke.task.Task;
import duke.task.TaskList;

public class DeadlineCommand implements Command {
    private Task toBeAdded;

    public DeadlineCommand(Task toBeAdded) {
        this.toBeAdded = toBeAdded;
    }

    @Override
    public String execute(TaskList tasks) {
        // Do TaskList stuff
        tasks.add(toBeAdded);
        // Do UI stuff
        return Ui.printAdd(toBeAdded.getDescription() + "\n", tasks.length());
        // Do storage stuff
        // tbc
    }
}
