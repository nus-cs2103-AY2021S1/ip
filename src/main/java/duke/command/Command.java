package duke.command;

import duke.TaskList;

public abstract class Command {
    protected String commandText;

    public abstract void execute(String text, TaskList taskList);
}
