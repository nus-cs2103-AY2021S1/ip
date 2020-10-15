package duke.parser;

import java.time.LocalDateTime;

import duke.exception.DukeParseException;
import duke.expense.Expense;
import duke.expense.Payable;
import duke.expense.Receivable;
import duke.storage.Storable;
import duke.utils.Datetime;
import duke.utils.Utils;

/**
 * Represents a class that parses lines in a saved storage text file into actual <code>Expenses</code>.
 * This class also parses <code>Expense</code> into <code>Strings</code>
 * that will be saved into the storage text file.
 */
public class ExpenseStorageParser implements StorageParser<Expense> {
    private static final int DESCRIPTION_INDEX = 1;
    private static final int VALUE_INDEX = 2;
    private static final int DATE_INDEX = 3;

    private static final int STORAGE_LENGTH = 4;

    /**
     * Parses a <code>String</code> into <code>double</code> value.
     *
     * @param money the <code>String</code> to be parsed.
     * @return the parsed value.
     * @throws DukeParseException if the given <code>String</code> is in a wrong format.
     */
    private double parseMoney(String money) throws DukeParseException {
        if (!Utils.isMoney(money)) {
            String msg = String.format(
                    "It appears the value '%s' of this expense is corrupted.", money);
            throw new DukeParseException(msg);
        }
        return Utils.convertMoneyToValue(money);
    }

    /**
     * Parses a given <code>String</code> date into a <code>LocalDateTime</code>.
     *
     * @param stringDate the <code>String</code> to be parsed.
     * @return the parsed <code>LocalDateTime</code>.
     * @throws DukeParseException if the <code>String</code> cannot be parsed.
     */
    private LocalDateTime parseDate(String stringDate) throws DukeParseException {
        try {
            return Datetime.parseDateString(stringDate, Expense.DATE_FORMAT_OUTPUT);
        } catch (DukeParseException exception) {
            String msg = String.format(
                    "It appears the date of this expense: '%s' is corrupted.", stringDate);
            throw new DukeParseException(msg);
        }
    }

    /**
     * Parses storage <code>Strings</code> into <code>Expenses</code>.
     *
     * @param storageString the <code>String</code> to be parsed.
     * @return the parsed <code>Expense</code>.
     * @throws DukeParseException if there are any errors in parsing.
     */
    @Override
    public Expense parseStorageString(String storageString) throws DukeParseException {
        String[] storageExpense = storageString.split(Storable.DELIMITER);
        assert storageExpense.length > 0 : "There is an error in the splitting of the storageExpenseString";

        if (storageExpense.length < STORAGE_LENGTH) {
            String msg = String.format("It appears this event: '%s' is corrupted.", storageString);
            throw new DukeParseException(msg);
        }

        String description = storageExpense[DESCRIPTION_INDEX];
        String dateString = storageExpense[DATE_INDEX];
        String moneyString = storageExpense[VALUE_INDEX];

        LocalDateTime date = parseDate(dateString);
        double value = parseMoney(moneyString);

        switch(storageExpense[0]) {
        case Payable.PAYABLE_SYMBOL:
            return new Payable(description, value, date);
        case Receivable.RECEIVABLE_SYMBOL:
            return new Receivable(description, value, date);
        default:
            String msg = String.format("It appears this line: '%s' is corrupted.", storageString);
            throw new DukeParseException(msg);
        }
    }
}
