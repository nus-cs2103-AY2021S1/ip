package main.java.Commands;

import main.java.Task.TaskList;

public class listCommand extends Command {

    public listCommand(TaskList tasklist) {
        super(tasklist);
    }

    /**
     * Executes the Command to show the Task List.
     */
    @Override
    public void execute(){
        this.tasklist.showList();
    }
}
