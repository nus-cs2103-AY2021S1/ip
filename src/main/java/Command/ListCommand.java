package Command;

import Task.TaskList;

import Command.Command;

import ParserStorageUi.*;

public class ListCommand extends Command {

    public ListCommand(String command){
        super(command);
    }

    /** Executes the command **/
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showTasks(tasks);
    }

    /** Check if the current command is an exit command **/
    @Override
    public boolean isExit(){
        return false;
    }

}
