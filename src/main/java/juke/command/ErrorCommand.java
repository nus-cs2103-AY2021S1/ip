package juke.command;

import juke.Storage;
import juke.TaskList;

public class ErrorCommand extends Command {

    protected String message;

    public ErrorCommand(String message) {
        super();
        this.message = message;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return this.message;
    }
}

