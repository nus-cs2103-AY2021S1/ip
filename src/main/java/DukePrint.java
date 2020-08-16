public class DukePrint {

    public static void print(String str) {
        System.out.println(wrapWithLines(str));
    }

    private static String wrapWithLines(String str) {
        return line() + "\n" + str + "\n" + line();
    }

    private static String line() {
        return "\t" + "_".repeat(50);
    }
}
