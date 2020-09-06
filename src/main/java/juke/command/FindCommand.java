package juke.command;

import juke.Storage;
import juke.TaskList;

public class FindCommand extends Command {

    protected String keyword;

    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    @Override
    public String executeCommand(TaskList taskList, Storage storage) {
        return taskList.findTasks(this.keyword);
    }
}
