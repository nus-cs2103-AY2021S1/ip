package duke.command;

import duke.Task;
import duke.TaskList;
import duke.Ui;

public class ToDoCommand implements Command {
    Task toBeAdded;

    public ToDoCommand(Task toBeAdded) {
        this.toBeAdded = toBeAdded;
    }

    @Override
    public void execute(TaskList tasks) {
        // Do TaskList stuff
        tasks.add(toBeAdded);
        // Do UI stuff
        Ui.printAdd(toBeAdded.getDescription() + "\n", tasks.length());
        // Do storage stuff
        // tbc
    }
}
