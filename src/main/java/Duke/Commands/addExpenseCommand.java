package main.java.Duke.Commands;

import main.java.Duke.Task.Expense;
import main.java.Duke.Task.Task;
import main.java.Duke.Task.TaskList;

public class addExpenseCommand extends addCommand{
    public addExpenseCommand(TaskList tasklist, Task task) {
        super(tasklist, task);
    }

    @Override
    public String execute(){
        return tasklist.addExpense((Expense)task);
    }
}
