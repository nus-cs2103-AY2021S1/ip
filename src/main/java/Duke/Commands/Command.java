package Duke.Commands;
import Duke.Errors.DukeException;
import Duke.Errors.FileAbsentException;
import Duke.Helpers.Storage;
import Duke.Helpers.TaskList;
import Duke.Helpers.Ui;
import Duke.Tasks.Task;

import java.io.FileWriter;
import java.io.IOException;

abstract public class Command {
    String string;
    boolean exit = false;
    Command(String string){
        this.string = string;
    }
    public boolean isExit(){
        return exit;
    }


    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
    }
}
