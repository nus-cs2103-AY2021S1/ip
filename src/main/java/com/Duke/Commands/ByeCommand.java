package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.TaskList;
import com.Duke.TaskManager.UI;

/**
 * This class acts as a model for the execution of the ByeCommand
 */
public class ByeCommand extends Command {

    private final TaskList taskList;

    public ByeCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public boolean isDone() {
        return true;
    }

    /**
     * Simulates the execution of the Bye command by Duke
     * @return The response to the command "bye".
     */
    public String execute() {
        try {
            return UI.byeCalled(taskList);
        } catch (DukeException e) {
            return UI.printError(e.toString());
        }
    }
}
