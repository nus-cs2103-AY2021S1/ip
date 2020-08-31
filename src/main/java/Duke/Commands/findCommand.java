package main.java.Duke.Commands;

import main.java.Duke.Task.TaskList;

public class findCommand extends Command {

    public findCommand(TaskList tasklist, String str) {
        super(tasklist,str);
    }

    @Override
    public String execute() {
        return this.tasklist.findTask(this.str);
    }
}
