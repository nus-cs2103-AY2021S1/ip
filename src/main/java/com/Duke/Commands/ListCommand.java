package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;

public class ListCommand extends Command{

    private final TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    public String execute(){
        try {
            return UI.listCalled(taskList);
        }catch(DukeException e){
            return UI.printError(e.toString());
        }
    }
}
