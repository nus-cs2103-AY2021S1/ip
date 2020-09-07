package duke.operation.addexpenseoperation;

import java.time.LocalDateTime;

import duke.expense.Expense;
import duke.list.ExpenseList;
import duke.operation.Operation;
import duke.result.Result;

/** Abstract class representing the operations that add various types of <code>Expenses</code>. */
public abstract class AddExpenseOperation extends Operation {
    protected final String description;
    protected final double value;
    protected final LocalDateTime date;
    protected final ExpenseList expenseList;

    /**
     * Constructor method.
     *
     * @param description description of the <code>Expense</code>.
     * @param value the value of the <code>Expense</code>.
     * @param date the date of the <code>Expense</code>.
     * @param expenseList the <code>ExpenseList</code> to be added into.
     */
    AddExpenseOperation(String description, double value, LocalDateTime date, ExpenseList expenseList) {
        this.description = description;
        this.value = value;
        this.date = date;
        this.expenseList = expenseList;
    }

    /**
     * Creates the associated <code>Expense</code>.
     *
     * @return a <code>Expense</code>.
     */
    public abstract Expense createExpense();

    /**
     * Executes the <code>AddExpenseOperation</code>.
     * The <code>Expense</code> is added to the <code>ExpenseList</code>.
     *
     * @return the <code>Result</code> of the exeucted <code>Operation</code>.
     */
    @Override
    public Result execute() {
        Expense newExpense = createExpense();
        expenseList.add(newExpense);
        String message = "I have added the expense:\n" + newExpense + "\n"
                + String.format("You now have %d expenses.", expenseList.getCurrCapacity());
        return new Result(true, message, this.isExit());
    }
}
