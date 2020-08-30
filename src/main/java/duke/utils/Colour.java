package duke.utils;

/**
 * The Colour class is used to wrap a string with colours.
 */
public class Colour {
    @SuppressWarnings("checkstyle:MethodName")
    public static String convertTextToGreen(String element) {
        return "\033[0;32m" + element + "\033[0m";
    }

    @SuppressWarnings("checkstyle:MethodName")
    public static String convertTextToRed(String element) {
        return "\033[0;31m" + element + "\033[0m";
    }
}
