package duke.storage;

import java.io.File;

import duke.expense.Expense;
import duke.parser.ExpenseStorageParser;

/** Class that loads and converts the <code>ExpenseList</code> in Duke with a text file. */
public class ExpenseStorage extends Storage<Expense> {
    private static final String DEFAULT_FILENAME = "expenses_storage.txt";

    /**
     * Constructor method.
     * Instantiates the actual path to the text file and an <code>ExpenseStorageParser</code>.
     *
     * @param path the path of the directory.
     */
    ExpenseStorage(String path) {
        super(new File(path + DEFAULT_FILENAME), new ExpenseStorageParser());
    }

    @Override
    String getStorableName() {
        return "expense";
    }
}
