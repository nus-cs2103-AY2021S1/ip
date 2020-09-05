package duke.list;

public class ListManager {
    private final TaskList taskList;
    private final ExpenseList expenseList;

    public ListManager() {
        this.taskList = new TaskList();
        this.expenseList = new ExpenseList();
    }

    public TaskList getTaskList() {
        return taskList;
    }

    public ExpenseList getExpenseList() {
        return expenseList;
    }
}
