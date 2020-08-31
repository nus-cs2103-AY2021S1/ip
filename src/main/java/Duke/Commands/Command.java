package main.java.Duke.Commands;


import main.java.Duke.Task.TaskList;

public class Command {
    public boolean isExit;
    TaskList tasklist;
    String str;

    public Command(TaskList tasklist, String str) {
        this.isExit = false;
        this.tasklist = tasklist;
        this.str = str;
    }

    /**
     * Executes the Command.
     */
    public String execute() {
        return this.str;
    }

}
