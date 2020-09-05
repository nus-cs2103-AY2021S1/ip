package duke.list;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import duke.expense.ExpenseStub;

public class StorageListTest {
    private static final ExpenseStub EXPENSE_ONE = new ExpenseStub("expense 1", 30.0);
    private static final ExpenseStub EXPENSE_TWO = new ExpenseStub("expense 2", 3);

    @Test
    public void testTaskList() {
        ExpenseList expenseList = new ExpenseList();
        assertEquals(expenseList.getCurrCapacity(), 0);

        assertEquals(expenseList.addExpense(EXPENSE_ONE), EXPENSE_ONE);
        assertEquals(expenseList.getCurrCapacity(), 1);

        assertEquals(expenseList.addExpense(EXPENSE_TWO), EXPENSE_TWO);
        assertEquals(expenseList.getCurrCapacity(), 2);
    }
}
