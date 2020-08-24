package main.java.Commands;

import main.java.Task.TaskList;

public class byeCommand extends Command {
    public byeCommand(TaskList taskList){
        super(taskList);
    }

    /**
     * Executes the command to end the loop in the main method and end the program.
     */
    @Override
    public void execute()
    {
        this.isExit = true;
    }
}
