import java.util.Scanner;

public class Parser {

    public Parser() {
    }

    public static String parseCmd(String s) {
        return s.toUpperCase().trim();
    }

    public static int parseInt(String str) {
        return Integer.parseInt(str.substring(str.length() - 1)) - 1;
    }

    public static String[] parse(String[] arr, int i) {
        String secondStr = arr[1];
        if (i == 1) {
            // For Deadline
            return secondStr.split(" /by ", 2);
        } else {
            // For Event
            return secondStr.split(" /at ", 2);
        }
    }
}
