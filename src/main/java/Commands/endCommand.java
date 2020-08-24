package main.java.Commands;

import main.java.Task.TaskList;

public class endCommand extends Command {

    public endCommand(TaskList taskList){
        super(taskList);
    }

    @Override
    public void execute() {
        this.isExit = true;
    }
}
