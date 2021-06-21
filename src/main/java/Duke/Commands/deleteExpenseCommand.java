package main.java.Duke.Commands;

import main.java.Duke.Task.TaskList;

public class deleteExpenseCommand extends deleteCommand {
    public deleteExpenseCommand(TaskList tasklist, int expenseNumber) {
        super(tasklist, expenseNumber);
    }

    @Override
    public String execute(){
        return this.tasklist.deleteExpense(taskNumber);
    }
}
