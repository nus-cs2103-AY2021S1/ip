package duke.utils;

/**
 * Text UI of the application.
 */

public class Ui {
    private static final int DIVIDER_LENGTH = 80;
    private static final int LEFT_PADDING = 7;

    private void printLeftPadding() {
        for (int i = 0; i < LEFT_PADDING; i++) {
            System.out.print(' ');
        }
    }

    private void printDivider() {
        for (int i = 0; i < DIVIDER_LENGTH - 1; i++) {
            System.out.print("_");
        }
        System.out.print("_\n");
    }

    /**
     * Prints out the output in the appropriate format with the padding and divider.
     * @param dukeOutputMessage the output that needs to be formatted.
     */
    public void print(String dukeOutputMessage) {
        printLeftPadding();
        printDivider();
        String[] lines = dukeOutputMessage.split("\\r?\\n");
        for (String line : lines) {
            printLeftPadding();
            System.out.println(line);
        }
        printLeftPadding();
        printDivider();
    }

}
