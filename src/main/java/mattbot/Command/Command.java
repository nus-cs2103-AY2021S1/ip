package mattbot.Command;

import java.time.LocalDateTime;

import mattbot.Errors.ErrorExceptions;

/**
 * Represents the overall command abstract class for sub-class to follow.
 */
public abstract class Command {

    /**
     * Sets the method to be followed by sub-class.
     * @param s String.
     */
    public static void execute(String s) throws ErrorExceptions {}

    /**
     * Sets the method to be followed by sub-class.
     */
    public static void execute() {}

    /**
     * Sets the method to be followed by sub-class.
     * @param i integer.
     */
    public static void execute(int i) throws ErrorExceptions {}

    /**
     * Sets the method to be followed by sub-class.
     * @param date date and time.
     */
    public static void execute(LocalDateTime date) {}
}
