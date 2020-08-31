package main.java.Duke.Commands;

import main.java.Duke.Task.TaskList;

public class endCommand extends Command {

    public endCommand(TaskList taskList) {
        super(taskList,"");
    }

    /**
     * Executes the command to end the loop in the main method and end the program.
     */
    @Override
    public String execute() {
        this.isExit = true;
        return "";
    }
}
