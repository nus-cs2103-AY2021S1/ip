package main.java.Duke.Commands;


import main.java.Duke.Task.TaskList;

public class exceptionCommand extends Command {
    public boolean isExit;
    TaskList tasklist;

    public exceptionCommand(TaskList tasklist,String str) {
        super(tasklist,str);
    }

    /**
     * Executes the Command.
     */
    public String execute() {
        return this.str;
    }

}