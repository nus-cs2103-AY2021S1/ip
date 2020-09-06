package main.java.Duke.Commands;

import main.java.Duke.Task.TaskList;

public class listExpenseCommand extends listCommand {

    public listExpenseCommand(TaskList tasklist) {
        super(tasklist);
    }

    @Override
    public String execute(){
        return this.tasklist.showExpenses();
    }
}
