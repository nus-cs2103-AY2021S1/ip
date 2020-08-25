package duke.utils;

public class Ui {
    private final static int DIVIDER_LENGTH = 80;
    private final static int LEFT_PADDING = 7;

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
