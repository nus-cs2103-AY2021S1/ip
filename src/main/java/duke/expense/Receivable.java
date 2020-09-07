package duke.expense;

import java.time.LocalDateTime;

/** Represents a Receivable expense. */
public class Receivable extends Expense {
    public static final String RECEIVABLE_SYMBOL = "R";

    /**
     * Constructor method.
     *
     * @param description of the <code>Receivable</code>.
     * @param value the amount of money that was received.
     * @param date the date where the transaction occurred.
     */
    public Receivable(String description, double value, LocalDateTime date) {
        super(description, value, date);
    }

    /**
     * Determines that this is a <code>Receivable</code>.
     *
     * @return <code>false</code>.
     */
    @Override
    public boolean isPayable() {
        return false;
    }

    /**
     * Gets the amount of money that is received.
     *
     * @return a positive <code>double</code> that represents money being transacted in.
     */
    @Override
    public double getValue() {
        return value;
    }

    /**
     * Gets the <code>Receivable</code> symbol.
     *
     * @return the <code>Receivable</code> symbol.
     */
    @Override
    public String getExpenseSymbol() {
        return RECEIVABLE_SYMBOL;
    }

    /**
     * Checks if this is equal to the other object.
     *
     * @param other the other object to be compared to.
     * @return <code>true</code> if equal.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        } else if (other instanceof Receivable) {
            Receivable otherReceivable = (Receivable) other;
            return this.isEqual(otherReceivable);
        }
        return false;
    }

    /**
     * Returns a <code>String</code> representing the <code>Receivable</code>.
     *
     * @return a <code>String</code> representing the <code>Receivable</code>.
     */
    @Override
    public String toString() {
        return "[" + RECEIVABLE_SYMBOL + "] " + toStringSuffix();
    }
}
