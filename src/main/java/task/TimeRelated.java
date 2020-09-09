package task;

/**
 * Contracts date and time getters.
 *
 * <p>The 'TimeRelated' supports operators, supported include: </p>
 *
 * <p> (i) Getters to date and time input as String </p>
 */
public interface TimeRelated {

    /**
     * Gets the date inputted by user in string.
     *
     * @return date inputted by user.
     */
    public String getDateInput();

    /**
     * Gets the date inputted by user in string.
     *
     * @return time inputted by user.
     */
    public String getTimeInput();
}
