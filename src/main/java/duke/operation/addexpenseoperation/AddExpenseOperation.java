package duke.operation.addexpenseoperation;

import duke.expense.Expense;
import duke.operation.Operation;
import duke.result.Result;

import java.time.LocalDateTime;

/** Abstract class representing the operations that add various types of <code>Expenses</code>. */
public abstract class AddExpenseOperation extends Operation {
    protected final String description;
    protected final double value;
    protected final LocalDateTime date;

    /**
     * Constructor method.
     *
     * @param description description of the <code>Expense</code>.
     * @param value the value of the <code>Expense</code>.
     * @param date the date of the <code>Expense</code>.
     */
    AddExpenseOperation(String description, double value, LocalDateTime date) {
        this.description = description;
        this.value = value;
        this.date = date;
    }

    /**
     * Creates the associated <code>Task</code>.
     *
     * @return an uncompleted <code>Task</code>.
     */
    public abstract Expense createExpense();

    /**
     * Specifies that this is not an <code>ExitOperation</code>.
     *
     * @return <code>false</code>.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public Result execute() {
        String message = "Expense: " + createExpense().toString();
        return new Result(true, message, this.isExit());
    }
}
