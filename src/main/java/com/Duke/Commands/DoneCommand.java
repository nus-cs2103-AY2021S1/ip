package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;

/**
 * This class acts as a model for the execution of the DoneCommand
 */
public class DoneCommand extends Command{

    private final TaskList taskList;
    private final int taskIndex;

    public DoneCommand(TaskList taskList, int taskIndex) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    /**
     * Simulates Duke executing the Done Command
     * @return The response to the command "done"
     */
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
