package duke.command;

import duke.Storage;
import duke.task.TaskList;
import duke.Ui;

public abstract class Command {
    boolean isExit;
    String command;

    Command(String command){
        this.command = command;
        this.isExit = false;
    }

    public void execute(TaskList list, Ui ui, Storage saveData){};

    public boolean isExit(){
        return false;
    };
}
