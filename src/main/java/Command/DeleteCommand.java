package Command;

import Command.Command;
import ParserStorageUi.*;
import Task.*;

public class DeleteCommand extends Command {

    public DeleteCommand(String command){
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        tasks = tasks.delete(this.command);
        ui.showDeletedTask(tasks.taskSize(), tasks.getAddedOrDeletedTask());
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
