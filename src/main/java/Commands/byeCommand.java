package main.java.Commands;

import main.java.Task.TaskList;

public class byeCommand extends Command {
    public byeCommand(TaskList taskList){
        super(taskList);
    }

    @Override
    public void execute()
    {
        this.isExit = true;
    }
}
