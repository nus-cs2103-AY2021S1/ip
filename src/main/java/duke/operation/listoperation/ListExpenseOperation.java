package duke.operation.listoperation;

import duke.list.ExpenseList;

public class ListExpenseOperation extends ListOperation {
    /**
     * Constructor method.
     *
     * @param expenseList the <code>ExpenseList</code> that is to be printed.
     */
    public ListExpenseOperation(ExpenseList expenseList) {
        super(expenseList);
    }

    @Override
    protected String getMessage() {
        return "Here are your expenses:\n" + this.list.toString();
    }
}
