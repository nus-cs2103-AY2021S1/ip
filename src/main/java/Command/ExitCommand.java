package Command;

import Command.Command;
import Task.TaskList;
import ParserStorageUi.*;
public class ExitCommand extends Command {

    public ExitCommand(String command){
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        storage.putToDatabase(tasks.getTaskList());
        ui.showGoodBye();
    }

    @Override
    public boolean isExit(){
        return true;
    }
}
