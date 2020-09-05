package duke.list;

import duke.expense.Expense;

import java.util.ArrayList;
import java.util.Iterator;

public class ExpenseList implements Iterable<Expense>, DukeList {
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

    @Override
    public int getCurrCapacity() {
        return this.expenseList.size();
    }

    /**
     * Converts the <code>ExpenseList</code> to a representative <code>String</code>.
     * This <code>String</code> contains all details of <code>Expenses</code>
     * stored in <code>ExpenseList</code>.
     *
     * @return the <code>String</code> representing <code>ExpenseList</code>.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.expenseList.size(); i++) {
            String expenseLine = String.format("%d. %s", i + 1, this.expenseList.get(i));
            sb.append(expenseLine);
            sb.append("\n");
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
