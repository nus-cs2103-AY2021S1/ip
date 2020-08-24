package Command;
import Task.TaskList;
import Command.Command;
import ParserStorageUi.*;

public class ListCommand extends Command {

    public ListCommand(String command){
        super(command);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showTasks(tasks);
    }

    @Override
    public boolean isExit(){
        return false;
    }

}
