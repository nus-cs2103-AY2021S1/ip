package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;

public class DoneCommand extends Command{

    private final TaskList ls;
    private final int taskIndex;

    public DoneCommand(TaskList ls, int taskIndex) {
        this.ls = ls;
        this.taskIndex = taskIndex;
    }

    public String execute(){
        try{
            return UI.doneCalled(ls,taskIndex);
        }catch (DukeException e){
            return UI.printError(e.toString());
        }catch(Exception e){
            return UI.printError("     ☹ OOPS!!! There arent that many tasks in your list");
        }
    }
}
