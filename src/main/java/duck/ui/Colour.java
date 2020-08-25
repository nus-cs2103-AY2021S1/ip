package duck.ui;

public class Colour {
    public static String Red(String s) {
        return "\033[0;31m" + s + "\033[0m";
    }

    public static String Green(String s) {
        return "\033[0;32m" + s + "\033[0m";
    }

    public static String Cyan(String s) {
        return "\033[0;96m" + s + "\033[0m";
    }

    public static String Magenta(String s) {
        return "\033[0;95m" + s + "\033[0m";
    }

    public static String Yellow(String s) {
        return "\033[0;93m" + s + "\033[0m";
    }

    public static String Blue(String s) {
        return "\033[0;34m" + s + "\033[0m";
    }
}
