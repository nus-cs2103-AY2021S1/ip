package duke.command;

import duke.task.Task;

public class TodoCommand extends AddAbstractTaskCommand {

    public TodoCommand(Task t) {
        super(t);
    }
}
