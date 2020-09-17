package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;
/**
 * This class acts as a model for the execution of the ListCommand
 */
public class ListCommand extends Command{

    private final TaskList taskList;

    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }
    /**
     * Simulates Duke executing the List Command
     * @return The response to the command "list"
     */
    public String execute(){
        try {
            return UI.listCalled(taskList);
        }catch(DukeException e){
            return UI.printError(e.toString());
        }
    }
}
