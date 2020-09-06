package duke.storage;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import duke.exception.DukeOperationException;
import duke.exception.DukeParseException;
import duke.expense.Expense;
import duke.list.ExpenseList;
import duke.parser.ExpenseStorageParser;

/** Class that loads and converts the <code>ExpenseList</code> in Duke with a text file. */
public class ExpenseStorage extends Storage {
    private static final String DEFAULT_FILENAME = "expenses_storage.txt";
    private final ExpenseStorageParser expenseStorageParser;

    /**
     * Constructor method.
     * Instantiates the actual path to the text file and an <code>ExpenseStorageParser</code>.
     *
     * @param path the path of the directory.
     */
    ExpenseStorage(String path) {
        super(new File(path + DEFAULT_FILENAME));
        this.expenseStorageParser = new ExpenseStorageParser();
    }

    /**
     * Loads the <code>ExpenseList</code> from the text file.
     *
     * @param expenseList the <code>ExpenseList</code> to be loaded onto.
     * @return a <code>String</code> indicating the status of the loading.
     */
    public String loadExpenseList(ExpenseList expenseList) {
        Scanner sc;
        try {
            sc = new Scanner(this.file);
        } catch (IOException ignore) {
            return "I detect no expense storage files. I shall create a fresh list.";
        }

        StringBuilder sb = new StringBuilder();
        while (sc.hasNext()) {
            try {
                Expense expense = this.expenseStorageParser.convertStorageToExpense(sc.nextLine());
                expenseList.add(expense);
            } catch (DukeParseException exception) {
                sb.append(exception.getMessage());
                sb.append("\n");
            }
        }

        if (sb.length() == 0) {
            return "All expenses have been loaded.";
        } else {
            sb.deleteCharAt(sb.length() - 1);
            return "The following expenses could not be loaded:\n" + sb.toString();
        }
    }

    /**
     * Saves the <code>ExpenseList</code> into the text file.
     *
     * @param expenseList the <code>ExpenseList</code> that is to be saved.
     * @throws DukeOperationException if the text file cannot be written onto.
     */
    public void saveToDisk(ExpenseList expenseList) throws DukeOperationException {
        StringBuilder sb = new StringBuilder();
        for (Expense expense : expenseList) {
            String storageExpense = expense.convertToStorageString();
            sb.append(storageExpense);
        }
        try {
            writeToFile(sb.toString());
        } catch (IOException exception) {
            throw new DukeOperationException("There were some problems when writing to the file. "
                    + exception.getMessage());
        }
    }
}
