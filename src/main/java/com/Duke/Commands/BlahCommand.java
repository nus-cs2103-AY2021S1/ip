package com.Duke.Commands;

import com.Duke.ProcessManager.DukeException;
import com.Duke.ProcessManager.UI;

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
