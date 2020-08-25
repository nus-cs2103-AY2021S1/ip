package duke.command;

import duke.task.TaskList;

public class listCommand extends Command {

    public listCommand(TaskList tasks) {
        super(tasks);
    }

    @Override
    public void execute() {
       this.tasks.list();
    }
}
