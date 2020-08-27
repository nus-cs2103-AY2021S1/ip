package com.Duke.Commands;

import com.Duke.TaskManager.DukeException;
import com.Duke.TaskManager.UI;

public class BlahCommand extends Command{

    @Override
    public void execute() {
        try{
            UI.blahCalled();
        }catch (DukeException e){
            UI.printError(e.toString());
        }
    }
}
