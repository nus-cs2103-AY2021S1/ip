package duke.list;

import java.util.ArrayList;

import duke.expense.Expense;

/** Represents the list of <code>Expenses</code>.*/
public class ExpenseList extends StorableList<Expense> {

    /**
     * Constructor method.
     * Initialise a new <code>ArrayList</code> of <code>Expenses</code>.
     */
    public ExpenseList() {
        super(new ArrayList<>());
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
        for (int i = 0; i < getCurrCapacity(); i++) {
            Expense expense = list.get(i);
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
