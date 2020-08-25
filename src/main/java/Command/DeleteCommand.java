package Command;

import Command.Command;
import Exceptions.MissingSpecifiedDeleteError;
import Exceptions.WrongIndexError;
import ParserStorageUi.*;
import Task.*;

public class DeleteCommand extends Command {

    public DeleteCommand(String command){
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MissingSpecifiedDeleteError ,WrongIndexError{
        try {
            tasks = tasks.delete(this.command);
            ui.showDeletedTask(tasks.taskSize(), tasks.getAddedOrDeletedTask());
        } catch (MissingSpecifiedDeleteError e) {
            throw new MissingSpecifiedDeleteError(e.getMessage());
        } catch (WrongIndexError e){
            throw new WrongIndexError(e.getMessage());
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }

    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        } else if (o instanceof DeleteCommand){
            DeleteCommand temp = (DeleteCommand) o;
            if (temp.command.equals(this.command)){
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
