package duke.operation.addexpenseoperation;

import duke.expense.Receivable;

import java.time.LocalDateTime;

public class AddReceivableOperation extends AddExpenseOperation {
    /**
     * Constructor method.
     *
     * @param description description of the <code>Receivable</code>.
     * @param value the value of the <code>Receivable</code>.
     * @param date the date of the <code>Receivable</code>.
     */
    public AddReceivableOperation(String description, double value, LocalDateTime date) {
        super(description, value, date);
    }

    /**
     * Creates the associated <code>Receivable</code>.
     *
     * @return a <code>Receivable</code>.
     */
    @Override
    public Receivable createExpense() {
        return new Receivable(description, value, date);
    }
}
