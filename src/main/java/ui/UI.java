package ui;


/**
 * Ui.UI class handles the scanner object and Ui.UI elements seen by user
 */

public class UI {

    public static final String LINE = "~~~~~~~~~~~~~~~~~~~~~";

    UI(){}

    /**
     * adds the LINEs to make it look nice
     *
     * @param content String item to be wrapped by two lines
     */
    public static String addLines(String content) {
        assert content.length() > 0;
        return LINE + "\n" + content + "\n" + LINE;
    }
}
