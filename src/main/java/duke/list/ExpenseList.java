package duke.list;

import java.util.ArrayList;
import java.util.Iterator;

import duke.expense.Expense;
import duke.storage.Storable;

/** Represents the list of <code>Expenses</code>.*/
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

    /**
     * Adds the <code>Expense</code> to <code>ExpenseList</code>.
     *
     * @param expense the <code>Expense</code> to be added.
     * @return the added <code>ExpenseList</code>.
     */
    public Expense addExpense(Expense expense) {
        this.expenseList.add(expense);
        return expense;
    }

    /**
     * Gets the number of <code>Expenses</code> currently.
     *
     * @return the number of <code>Expenses</code> in <code>ExpenseList</code>.
     */
    @Override
    public int getCurrCapacity() {
        return this.expenseList.size();
    }

    /**
     * Checks if the given <code>index</code> is within the capacity of <code>ExpenseList</code>.
     *
     * @param index the value to be checked on.
     * @return <code>true</code> if <code>index</code> is within the capacity.
     */
    @Override
    public boolean isValidIndex(int index) {
        return index <= expenseList.size() && index > 0;
    }

    /**
     * Removes a specified <code>Expense</code> from <code>ExpenseList</code>.
     *
     * @param index the index of the <code>Expense</code> that is to be deleted.
     * @return the <code>Expense</code> that has been removed.
     */
    @Override
    public Storable remove(int index) {
        return expenseList.remove(index - 1);
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

    /**
     * Finds all <code>Expenses</code> containing the specified search word.
     *
     * @param searchWord the <code>String</code> that is to be search with.
     * @return a <code>String</code> containing all <code>Expenses</code> that are found.
     */
    @Override
    public String search(String searchWord) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.getCurrCapacity(); i++) {
            Expense expense = expenseList.get(i);
            if (expense.getExpenseDescription().contains(searchWord)) {
                String expenseString = String.format("%d. %s", i + 1, expense.toString());
                sb.append(expenseString);
                sb.append("\n");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
}
