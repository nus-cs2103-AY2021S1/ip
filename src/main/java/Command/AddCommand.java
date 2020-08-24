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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws NoDateException, InvalidCommandException, NoTaskException {
        tasks = tasks.add(this.command);
        ui.showAddedTask(tasks.taskSize(), tasks.getAddedOrDeletedTask());
    }

    @Override
    public boolean isExit(){
        return false;
    }
}
