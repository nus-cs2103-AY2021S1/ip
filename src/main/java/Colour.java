public class Colour {
    public static String Red(String s) {
        return "\033[0;31m" + s + "\033[0m";
    }

    public static String Green(String s) {
        return "\033[0;32m" + s + "\033[0m";
    }

    public static String Brown(String s) {
        return "\033[0;33m" + s + "\033[0m";
    }

    public static String Blue(String s) {
        return "\033[0;34m" + s + "\033[0m";
    }
}
