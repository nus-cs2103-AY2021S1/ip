package duke.financial;

import duke.command.UserInputException;

/**
 * A category for managing/tracking finances.
 */
public class Category {
    /** The name of the category. */
    private String name;
    /** The total amount of money in the category. */
    private double totalAmount;
    /** The previous amount of money in the category. */
    private double previousAmount;

    /**
     * Constructs a new category object with total amount starting at 0.
     *
     * @param name The name to be used for identifying the category.
     */
    public Category(String name) {
        this.name = name;
        totalAmount = 0;
        previousAmount = 0;
    }

    /**
     * Adds a specified non-negative amount to the current total.
     *
     * @param amount The amount to be added to the total.
     * @throws UserInputException If the amount specified is negative.
     */
    public void addAmount(double amount) throws UserInputException {
        if (amount < 0) {
            throw UserInputException.INVALID_AMOUNT;
        }
        update();
        totalAmount += amount;
    }

    /**
     * Subtracts a specified non-negative amount from the current total.
     *
     * If the total were to go negative, sets it to 0 instead.
     *
     * @param amount The amount to be subtracted from the total.
     * @throws UserInputException If the amount specified is negative.
     */
    public void reduceAmount(double amount) throws UserInputException {
        if (amount < 0) {
            throw UserInputException.INVALID_AMOUNT;
        }
        update();
        if (amount > totalAmount) {
            totalAmount = 0;
            return;
        }
        totalAmount -= amount;
    }

    /**
     * Renames the category with a new specified name.
     *
     * @param newName The new name for the category.
     */
    public void rename(String newName) {
        name = newName;
    }

    /** Updates the previous amount to be the total amount. (For tracking purposes.)*/
    private void update() {
        previousAmount = totalAmount;
    }

    /**
     * Gets the previous amount in the category.
     *
     * @return The previous amount in the category.
     */
    public double getPreviousAmount() {
        return previousAmount;
    }

    /**
     * Gets the current total amount in the category.
     *
     * @return The current amount in the category.
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof Category)) {
            return false;
        }

        Category c = (Category) o;
        return name.equalsIgnoreCase(c.name);
    }

    @Override
    public String toString() {
        return String.format("%-15s:    %.02f", name, totalAmount);
    }
}
