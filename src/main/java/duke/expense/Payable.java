package duke.expense;

import java.time.LocalDateTime;

public class Payable extends Expense {
    public static final String PAYABLE_SYMBOL = "P";

    /**
     * Constructor method.
     *
     * @param description of the <code>Payable</code>.
     * @param value the amount of money that was paid.
     * @param date the date where the payment occurred.
     */
    public Payable(String description, double value, LocalDateTime date) {
        super(description, value, date);
    }

    /**
     * Determines that this is a <code>Payable</code>.
     *
     * @return <code>true</code>.
     */
    @Override
    public boolean isPayable() {
        return true;
    }

    /**
     * Gets the amount of money that is paid.
     *
     * @return a negative <code>double</code> that represents money being transacted out.
     */
    @Override
    public double getValue() {
        return this.value * -1;
    }

    /**
     * Returns a <code>String</code> representing the <code>Payable</code>.
     *
     * @return a <code>String</code> representing the <code>Payable</code>.
     */
    @Override
    public String toString() {
        return "[" + PAYABLE_SYMBOL + "]" + toStringSuffix();
    }

}
