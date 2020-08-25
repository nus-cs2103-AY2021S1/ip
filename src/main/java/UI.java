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
     * @param content String item to be wrapped by two lines
     */
    public static void addLines(String content) {
        System.out.print(line);
        System.out.print(content);
        System.out.println(line);
    }

    /**
     * introductory paragraph when Duke is first initialised
     */
    public static void introduction() {

        String introduction = "Hello! I'm Duke \nWhat can I do for you?";
        System.out.println(line);
        System.out.println(introduction);
        System.out.println(line);
    }
}
