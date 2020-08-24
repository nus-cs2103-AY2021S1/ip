package main.java.Commands;


import main.java.Task.TaskList;

public class Command {
    public boolean isExit;
    TaskList tasklist;

    public Command(TaskList tasklist){
        this.isExit = false;
        this.tasklist = tasklist;
    }

    /**
     * Executes the Command.
     */
    public void execute() {
    }

}
