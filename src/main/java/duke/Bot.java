package duke;

/**
 * This interface defines how other classes (eg. Commands, TaskListStorage) can control Duke.
 */
public interface Bot {

    /**
     * Tells Duke to exit after it finishes handling the current user input.
     */
    public void stop();

    /**
     * Make Duke say the given String.
     *
     * @param string the String for Duke to say
     */
    public void sayLine(String string);
}
