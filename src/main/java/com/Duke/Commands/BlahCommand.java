package com.Duke.Commands;

import com.Duke.TaskManager.UI;

/**
 * This class acts as a model for the execution of the BlahCommand
 */
public class BlahCommand extends Command {

    /**
     * Simulates Duke executing the blah Command
     * @return The response of any undefined command
     */
    @Override
    public String execute() {
        return UI.blahCalled();
    }


}
