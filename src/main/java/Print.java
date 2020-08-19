public class Print {
    public static void formatPrint(String str) {
        String divider = "----------------------------\n";
        String newStr = ("   " + divider + str + "\n" + divider).replaceAll("(\r\n|\n)", "\r\n   ");
        System.out.println(newStr);
    }
}
