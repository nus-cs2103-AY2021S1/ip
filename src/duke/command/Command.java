package duke.command;

import duke.main.TaskList;

public abstract class Command {

    abstract public void perform(TaskList tasks);
    abstract public boolean isExit();
}
