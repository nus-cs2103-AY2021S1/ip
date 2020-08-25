package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public abstract class Command {
    public String task;
    public Boolean isExit;

    public Command(String task) {
        this.task = task;
        this.isExit = false;
    }

    public Command(String task, Boolean isExit) {
        this.task = task;
        this.isExit = isExit;
    }

    public Command(Boolean isExit) {
        this.task = null;
        this.isExit = isExit;
    }

    public Command() {
        this.task = null;
        this.isExit = false;
    }

    public boolean isExit() {
        return this.isExit;
    }

    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

    public String getTask() {
        return this.task;
    };

}