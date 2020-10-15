package duke.operation.addexpenseoperation;

import java.time.LocalDateTime;

import duke.expense.Payable;
import duke.list.ExpenseList;

/** Represents the operation that adds a <code>Payable</code>. */
public class AddPayableOperation extends AddExpenseOperation {

    /**
     * Constructor method.
     *
     * @param description description of the <code>Payable</code>.
     * @param value the value of the <code>Payable</code>.
     * @param date the date of the <code>Payable</code>.
     * @param expenseList the <code>ExpenseList</code> to be added into.
     */
    public AddPayableOperation(
            String description, double value, LocalDateTime date, ExpenseList expenseList) {
        super(description, value, date, expenseList);
    }

    /**
     * Creates the associated <code>Payable</code>.
     *
     * @return a <code>Payable</code>.
     */
    @Override
    public Payable createExpense() {
        return new Payable(description, value, date);
    }
}
