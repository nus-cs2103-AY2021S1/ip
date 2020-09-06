import java.util.Scanner;


/**
 * UI class handles the scanner object and UI elements seen by user
 */

public class UI {

    public static final String line = "____________________________________________________________";
    public Scanner sc;

    UI() {
        this.sc = new Scanner(System.in);
    }

    /**
     * adds the lines to make it look nice
     *
     * @param content String item to be wrapped by two lines
     */
    public static String addLines(String content) {
        return line + "\n" + content + "\n" + line;
    }


}
