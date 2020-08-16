public class StringUtils {

    /**
     * Wraps and prints the strArr with indentation and top and bottom borders
     */
    public static void printWithWrapper(String[] strArr) {
        String BORDER = "=======================================";
        String INDENT = "    ";
        System.out.println(BORDER);
        for (String str : strArr) {
            System.out.println(INDENT + str);
        }
        System.out.println(BORDER);
    }
}
