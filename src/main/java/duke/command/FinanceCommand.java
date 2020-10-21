package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.financial.Category;
import duke.financial.FinanceList;

/**
 * A command dealing with tasks related to managing a finance-list.
 */
public class FinanceCommand extends Command {
    /** The type of the FinanceCommand. */
    private CommandType commandType;
    /** The name of the category involved. */
    private String categoryName;
    /** The new name for a category. */
    private String newName;
    /** The amount to be added or subtracted from a category. */
    private double amount;

    /**
     * Constructs a new FinanceCommand with a specified amount.
     *
     * @param commandType The type of the FinanceCommand.
     * @param categoryName The name of the category involved.
     * @param amount The amount to be added or subtracted from the category involved.
     */
    public FinanceCommand(CommandType commandType, String categoryName, double amount) {
        this.commandType = commandType;
        this.categoryName = categoryName;
        this.amount = amount;
    }

    /**
     * Constructs a new FinanceCommand with zero to two category names.
     *
     * @param commandType The type of the FinanceCommand.
     * @param names The variable arguments used by the command.
     */
    public FinanceCommand(CommandType commandType, String... names) {
        assert names.length > 0;
        this.commandType = commandType;
        categoryName = names[0];
        if (commandType.equals(CommandType.RENAME)) {
            assert names.length == 2;
            newName = names[1];
        }
    }

    @Override
    public void execute(Ui ui, Storage storage) throws DukeException {
        FinanceList finances = storage.getFinances();
        switch (commandType) {
        case NEW:
            Category category = new Category(categoryName);
            finances.addEntry(category);
            storage.addLine(category.toString());
            ui.showAddedCategory(category.toString());
            break;
        case REMOVE:
            int index = finances.indexOf(categoryName);
            category = finances.removeCategory(categoryName);
            storage.deleteLine(index + 1);
            ui.showRemovedCategory(category.toString());
            break;
        case ADD:
            index = finances.indexOf(categoryName);
            category = finances.addToCategory(categoryName, amount);
            storage.replaceLine(index + 1, category.toString());
            ui.showChangedAmount(categoryName, category.getPreviousAmount(), category.getTotalAmount());
            break;
        case REDUCE:
            index = finances.indexOf(categoryName);
            category = finances.reduceFromCategory(categoryName, amount);
            storage.replaceLine(index + 1, category.toString());
            ui.showChangedAmount(categoryName, category.getPreviousAmount(), category.getTotalAmount());
            break;
        case RENAME:
            index = finances.indexOf(categoryName);
            category = finances.renameCategory(categoryName, newName);
            storage.replaceLine(index + 1, category.toString());
            ui.showRenamedCategory(categoryName, newName);
            break;
        default:
            throw UserInputException.INVALID_COMMAND;
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
