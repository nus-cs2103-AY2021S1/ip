package duke.list;

import duke.expense.Expense;

import java.util.ArrayList;
import java.util.Iterator;

public class ExpenseList implements Iterable<Expense> {
    private final ArrayList<Expense> expenseList;

    /**
     * Constructor method.
     * Initialise a new <code>ExpenseList</code> of <code>Expenses</code>.
     */
    public ExpenseList() {
        this.expenseList = new ArrayList<>();
    }

    /**
     * Implements the <code>Iterable</code> interface in order for <code>ExpenseList</code> to be iterable.
     *
     * @return the iterator form of <code>ExpenseList</code>.
     */
    @Override
    public Iterator<Expense> iterator() {
        return this.expenseList.iterator();
    }

    public Expense addExpense(Expense expense) {
        this.expenseList.add(expense);
        return expense;
    }

    public int getCurrCapacity() {
        return this.expenseList.size();
    }
}
