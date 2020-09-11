package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;

public class FindCommand extends Command{
    private final TaskList taskList;
    private final String hint;

    public FindCommand(TaskList taskList, String hint) {
        this.taskList = taskList;
        this.hint = hint;
    }

    @Override
    public String execute() {
        try {
            return UI.findCalled(taskList, hint);
        }catch (DukeException e){
            return UI.printError(e.toString());
        }
    }
}
