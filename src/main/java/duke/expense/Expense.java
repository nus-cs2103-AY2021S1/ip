package duke.expense;

import duke.utils.Datetime;

import java.time.LocalDateTime;

/** Represents the user's expense. */
public abstract class Expense {
    public static final String DATE_FORMAT_INPUT = "dd-MM-yyyy";
    public static final String DATE_FORMAT_OUTPUT = "MMM dd yyyy";
    public static final String EXPENSE_BREAK = "/on";

    private final String description;
    protected final double value;
    private final Datetime date;

    /**
     * Constructor method.
     *
     * @param description of the <code>Expense</code>.
     * @param value the amount of money that was transferred.
     * @param date the date where the <code>Expense</code> occurred.
     */
    protected Expense(String description, double value, LocalDateTime date) {
        this.description = description;
        this.value = value;
        this.date = new Datetime(date, DATE_FORMAT_INPUT, DATE_FORMAT_OUTPUT);
    }

    /**
     * Checks if the <code>Expense</code> is a <code>Payable</code> or <code>Receivable</code>.
     *
     * @return <code>true</code> if it is a <code>Payable</code>.
     */
    public abstract boolean isPayable();

    /**
     * Gets the value of the <code>Expense</code>.
     *
     * @return a negative value if <code>Payable</code> and
     * positive value if <code>Receivable</code>
     */
    public abstract double getValue();

    /**
     * Converts the <code>Expense</code> to a <code>String</code>.
     * Indicates the date of the <code>Expense</code>, its description and its value.
     *
     * @return a <code>String</code> representing the <code>Expense</code>.
     */
    protected String toStringSuffix() {
        String date = this.date.getOutputDatetimeString();
        return String.format("%s (on: %s) Amount: $%.2f", this.description, date, this.value);
    }
}
