package Ultron.Commands;

import Ultron.Storage;
import Ultron.UI;
import Ultron.TaskList;
import Ultron.Exceptions.UltronException;

public abstract class Command {

    private final boolean isExit;
    private final String arguments;

    public Command(boolean isExit, String arguments){
        this.isExit = isExit;
        this.arguments = arguments;
    }

    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws UltronException;

    public boolean isExit(){
        return isExit;
    }

    protected String getArguments(){
        return this.arguments;
    }

}
