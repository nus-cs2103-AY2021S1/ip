package duke.utils;

public class Colour {
    public static String Green(String element) {
        return "\033[0;32m" + element + "\033[0m";
    }

    public static String Red(String element) {
        return "\033[0;31m" + element + "\033[0m";
    }
}
