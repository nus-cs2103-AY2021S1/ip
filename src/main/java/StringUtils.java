public class StringUtils {

    public static <T> void printWithWrapper(T[] arr, boolean withNumbering) {
        String BORDER = "=======================================";
        String INDENT = "    ";
        System.out.println(BORDER);
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null) {
                break;
            }

            String prepend = INDENT;
            if (withNumbering) {
                prepend += (i + 1) + ". ";
            }
            System.out.println(prepend + arr[i].toString());
        }
        System.out.println(BORDER);
    }
}
