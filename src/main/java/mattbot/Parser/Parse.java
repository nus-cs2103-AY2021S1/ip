package mattbot.Parser;

import mattbot.Errors.ErrorExceptions;

/**
 * Represents the base Parse object.
 */
public abstract class Parse {

    /**
     * Sets the methods for sub-class to inherit.
     * @param i integer
     */
    public static void execute(int i) throws ErrorExceptions {}

    /**
     * Sets the methods for sub-class to inherit.
     * @param s String
     */
    public static void execute(String s) throws ErrorExceptions {}

    /**
     * Sets the methods for sub-class to inherit.
     */
    public static void execute() {}
}
