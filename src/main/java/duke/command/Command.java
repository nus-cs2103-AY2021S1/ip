package duke.command;

import duke.TaskList;
import duke.ui.Ui;

public abstract class Command {

    protected TaskList taskList;
    protected Ui ui;

    public Command(TaskList taskList, Ui ui) {
        this.taskList = taskList;
        this.ui = ui;
    }

    public abstract void execute();

}
