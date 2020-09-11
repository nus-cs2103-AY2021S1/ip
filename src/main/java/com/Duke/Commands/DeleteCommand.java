package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;

/**
 * This class acts as a model for the execution of the DeleteCommand
 */
public class DeleteCommand extends Command{
    private final TaskList taskList;
    private final int taskIndex;

    public DeleteCommand(TaskList taskList, int taskIndex) {
        this.taskList = taskList;
        this.taskIndex = taskIndex;
    }

    /**
     * Simulates Duke executing the Delete Command
     * @return The response to the command "delete"
     */
    @Override
    public String execute() {
        try{
            return UI.deleteCalled(taskList, taskIndex);
        }catch(DukeException e){
            return UI.printError(e.toString());
        }
    }
}
