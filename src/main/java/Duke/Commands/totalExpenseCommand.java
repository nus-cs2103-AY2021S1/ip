package main.java.Duke.Commands;

import main.java.Duke.Task.TaskList;

public class totalExpenseCommand extends Command{

    public totalExpenseCommand(TaskList tasklist, String str) {
        super(tasklist, str);
    }

    @Override
    public String execute() {
        return "Total expenses: $" + String.valueOf(this.tasklist.calculateTotalExpense());
    }
}
