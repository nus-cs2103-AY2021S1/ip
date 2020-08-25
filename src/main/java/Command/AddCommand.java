package Command;

import Command.Command;
import Task.TaskList;
import ParserStorageUi.*;
import Exceptions.*;

public class AddCommand extends Command {

    public AddCommand(String command){
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoDateException, InvalidCommandException, NoTaskException,WrongDateTimeFormatException {
        try {
            tasks = tasks.add(this.command);
            ui.showAddedTask(tasks.taskSize(), tasks.getAddedOrDeletedTask());
        } catch (WrongDateTimeFormatException e){
            throw new WrongDateTimeFormatException(e.getMessage());
        }
    }

    @Override
    public boolean isExit(){
        return false;
    }


}
