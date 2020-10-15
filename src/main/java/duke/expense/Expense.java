package duke.expense;

import java.time.LocalDateTime;

import duke.storage.Storable;
import duke.utils.Datetime;

/** Represents the user's expense. */
public abstract class Expense implements Storable {
    public static final String DATE_FORMAT_INPUT = "dd-MM-yyyy";
    public static final String DATE_FORMAT_OUTPUT = "MMM dd yyyy";
    public static final String EXPENSE_BREAK = "/on";

    protected final double value;
    private final String description;
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

    /** Checks if the attributes that are found in <code>Expense</code> are equal.
     *
     * @param other the other <code>Expense</code> object to be compared to.
     * @return <code>true</code> if equal.
     */
    protected boolean isEqual(Expense other) {
        boolean isDescriptionEqual = description.equals(other.description);
        boolean isValueEqual = value == other.value;
        boolean isDateEqual = date.getOutputDatetimeString().equals(
                other.date.getOutputDatetimeString());
        return isDescriptionEqual && isValueEqual && isDateEqual;
    }

    /**
     * Gets the <code>String</code> description of <code>Expense</code>.
     *
     * @return the <code>String</code> description.
     */
    public String getExpenseDescription() {
        return this.description;
    }

    /**
     * Gets the actual calculable value of the <code>Expense</code>.
     *
     * @return a negative value if <code>Payable</code> and
     * positive value if <code>Receivable</code>
     */
    public abstract double getValue();

    /**
     * Gets the <code>String</code> value of <code>value</code>, rounded off to 2 decimal places.
     *
     * @return a <code>String</code> of value.
     */
    public String getPrintValue() {
        return String.format("$%.2f", value);
    }

    /**
     * Gets the <code>String</code> symbol of either
     * <code>Payable</code> or <code>Receivable</code>.
     *
     * @return the <code>String</code> symbol.
     */
    public abstract String getExpenseSymbol();

    /**
     * Gets the formatted <code>String</code> of the date.
     *
     * @return <code>String</code> of the date.
     */
    public String getExpenseDate() {
        return date.getOutputDatetimeString();
    }

    /**
     * Converts the <code>Expense</code> to a <code>String</code>.
     * Indicates the date, description, value of the <code>Expense</code>.
     *
     * @return a <code>String</code> representing the <code>Expense</code>.
     */
    protected String toStringSuffix() {
        String date = getExpenseDate();
        return String.format("%s (on: %s), %s", description, date, getPrintValue());
    }

    /**
     * Converts this to a <code>String</code> that will be saved onto the storage text file.
     *
     * @return the <code>String</code> representing the <code>Expense</code>.
     */
    public String convertToStorageString() {
        String symbol = getExpenseSymbol();
        String description = Storable.DELIMITER + getExpenseDescription();
        String value = Storable.DELIMITER + getPrintValue();
        String datetime = Storable.DELIMITER + getExpenseDate();
        return symbol + description + value + datetime + "\n";
    }
}
