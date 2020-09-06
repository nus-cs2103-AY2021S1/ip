package juke.command;

import juke.Storage;
import juke.TaskList;

public class DeleteCommand extends Command {

    protected Integer index;

    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return taskList.removeFromList(this.index);
    }
}