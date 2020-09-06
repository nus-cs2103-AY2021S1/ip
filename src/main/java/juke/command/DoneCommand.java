package juke.command;

import juke.Storage;
import juke.TaskList;

public class DoneCommand extends Command {

    protected Integer index;

    public DoneCommand(int index) {
        super();
        this.index = index;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return taskList.makeTaskDone(this.index);
    }
}

