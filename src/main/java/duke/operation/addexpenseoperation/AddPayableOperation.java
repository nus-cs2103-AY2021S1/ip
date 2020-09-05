package duke.operation.addexpenseoperation;

import duke.expense.Expense;
import duke.expense.Payable;

import java.time.LocalDateTime;

public class AddPayableOperation extends AddExpenseOperation {

    /**
     * Constructor method.
     *
     * @param description description of the <code>Payable</code>.
     * @param value the value of the <code>Payable</code>.
     * @param date the date of the <code>Payable</code>.
     */
    public AddPayableOperation(String description, double value, LocalDateTime date) {
        super(description, value, date);
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
