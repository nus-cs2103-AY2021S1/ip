package duke.financial;

import duke.DukeList;
import duke.command.UserInputException;

/**
 * A finance-list for storing or tracking changes in amount within categories.
 */
public class FinanceList extends DukeList<Category> {

    /**
     * Constructs a new finance-list with no categories.
     */
    public FinanceList() {}

    /**
     * Adds a specified amount to the total in a given category in the finance-list.
     *
     * @param name The name corresponding to the category.
     * @param amount The amount to be added to the category.
     * @return The category after it has been updated.
     * @throws UserInputException If there is no matching category in the finance-list, or if
     * the amount specified is negative.
     */
    public Category addToCategory(String name, double amount) throws UserInputException {
        Category category = getCategory(name);
        category.addAmount(amount);
        return category;
    }

    /**
     * Subtracts a specified amount from the total in a given category in the finance-list.
     *
     * @param name The name corresponding to the category.
     * @param amount The amount to be subtracted from the category.
     * @return The category after it has been updated.
     * @throws UserInputException If there is no matching category in the finance-list, or if
     * the amount specified is negative.
     */
    public Category reduceFromCategory(String name, double amount) throws UserInputException {
        Category category = getCategory(name);
        category.reduceAmount(amount);
        return category;
    }

    /**
     * Renames a given category in the finance-list to a new name.
     *
     * @param name The name corresponding to the category.
     * @param newName The new name to be given to the category.
     * @return The category after it has been renamed.
     * @throws UserInputException If there is no matching category in the finance-list.
     */
    public Category renameCategory(String name, String newName) throws UserInputException {
        Category category = getCategory(name);
        category.rename(newName);
        return category;
    }

    /**
     * Gets the category corresponding to the specified name, if it exists within the finance-list.
     *
     * @param name The name corresponding to the category.
     * @return The category from the finance-list identified by the specified name.
     * @throws UserInputException If there is no matching category in the finance-list.
     */
    public Category getCategory(String name) throws UserInputException {
        int index = indexOf(name);
        return entries.get(index);
    }

    /**
     * Gets the index of the category within the finance-list (index starts from 0).
     *
     * @param name The name corresponding to the category.
     * @return The index of the category.
     * @throws UserInputException If there is no matching category in the finance-list.
     */
    public int indexOf(String name) throws UserInputException {
        Category category = new Category(name);
        int index = entries.indexOf(category);
        if (index < 0) {
            throw UserInputException.NO_SUCH_CATEGORY;
        }
        return index;
    }

    /**
     * Removes and returns the category from the finance-list.
     *
     * @param name The name corresponding to the category.
     * @return The removed category.
     * @throws UserInputException If there is no matching category in the finance-list.
     */
    public Category removeCategory(String name) throws UserInputException {
        int index = indexOf(name);
        return entries.remove(index);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        boolean isFirst = true;
        for (Category c : entries) {
            if (isFirst) {
                isFirst = false;
            } else {
                stringBuilder.append("\n");
            }
            stringBuilder.append(c.toString());
        }
        return stringBuilder.toString();
    }
}
