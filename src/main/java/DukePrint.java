public class DukePrint {

    private static String LINE = "\t" + "_".repeat(50);

    public static void print(String str) {
        System.out.println(wrapWithLines(str));
    }

    private static String wrapWithLines(String str) {
        return LINE + "\n" + str + "\n" + LINE;
    }

}
