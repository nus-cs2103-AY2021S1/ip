package duke.expense;

import java.time.LocalDateTime;

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
        return this.value;
    }

    /**
     * Returns a <code>String</code> representing the <code>Receivable</code>.
     *
     * @return a <code>String</code> representing the <code>Receivable</code>.
     */
    @Override
    public String toString() {
        return "[" + RECEIVABLE_SYMBOL + "]" + toStringSuffix();
    }
}
