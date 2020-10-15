package duke.expense;

import java.time.LocalDateTime;

public class ExpenseStub extends Expense {
    private static final LocalDateTime DATE = LocalDateTime.now();

    public ExpenseStub(String description, double value) {
        super(description, value, DATE);
    }

    @Override
    public boolean isPayable() {
        return false;
    }

    @Override
    public double getValue() {
        return this.value;
    }

    @Override
    public String getExpenseSymbol() {
        return "ES";
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof ExpenseStub) {
            ExpenseStub otherStub = (ExpenseStub) other;
            return this.isEqual(otherStub);
        }
        return false;
    }
}
