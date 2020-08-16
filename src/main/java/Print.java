public class Print {
    private static final String lines = ".~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.~.\n";
    public static void print (String message) {
        System.out.println(lines + message + lines);
    }

    public static String printFormat (String message) {
        return lines + message + lines;
    }
}
