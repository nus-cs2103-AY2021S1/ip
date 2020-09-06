package juke.command;

import juke.Storage;
import juke.TaskList;

public class ListCommand extends Command {

    public ListCommand() {
        super();
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return taskList.printList();
    }

}
