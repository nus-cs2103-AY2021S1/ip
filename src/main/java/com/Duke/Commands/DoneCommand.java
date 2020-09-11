package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;

public class DoneCommand extends Command{

    private final TaskList taskList;
    private final int taskIndex;

    public DoneCommand(TaskList taskList, int taskIndex) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    public String execute(){
        try{
            return UI.doneCalled(taskList,taskIndex);
        }catch (DukeException e){
            return UI.printError(e.toString());
        }catch(Exception e){
            return UI.printError("     ☹ OOPS!!! There arent that many tasks in your list");
        }
    }
}
