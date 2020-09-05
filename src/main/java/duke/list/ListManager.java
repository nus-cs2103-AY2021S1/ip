package duke.list;

/** Class that manages the differnt <code>DukeLists</code>. */
public class ListManager {
    private final TaskList taskList;
    private final ExpenseList expenseList;

    /**
     * Constructor method.
     * Instantiates a new <code>TaskList</code> and <code>ExpenseList</code>.
     */
    public ListManager() {
        this.taskList = new TaskList();
        this.expenseList = new ExpenseList();
    }

    /**
     * Gets the <code>TaskList</code> of <code>ListManager</code>.
     *
     * @return the <code>TaskList</code>.
     */
    public TaskList getTaskList() {
        return taskList;
    }

    /**
     * Gets the <code>ExpenseList</code> of <code>ListManager</code>.
     *
     * @return the <code>ExpenseList</code>.
     */
    public ExpenseList getExpenseList() {
        return expenseList;
    }
}
