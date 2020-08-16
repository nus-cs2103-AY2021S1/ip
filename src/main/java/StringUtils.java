public class StringUtils {

    public static void printWithWrapper(String[] strArr, boolean withNumbering) {
        String BORDER = "=======================================";
        String INDENT = "    ";
        System.out.println(BORDER);
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i] == null) {
                break;
            }

            String prepend = INDENT;
            if (withNumbering) {
                prepend += (i + 1) + ". ";
            }
            System.out.println(prepend + strArr[i]);
        }
        System.out.println(BORDER);
    }
}
