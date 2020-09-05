package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.UI;

public class BlahCommand extends Command {

    @Override
    public String execute() {
        try {
            return UI.blahCalled();
        } catch (DukeException e) {
            return UI.printError(e.toString());
        }
    }


}
