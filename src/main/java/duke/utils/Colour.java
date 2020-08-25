package duke.utils;

/**
 * The Colour class is used to wrap a string with colours.
 */
public class Colour {
    public static String Green(String element) {
        return "\033[0;32m" + element + "\033[0m";
    }

    public static String Red(String element) {
        return "\033[0;31m" + element + "\033[0m";
    }
}
