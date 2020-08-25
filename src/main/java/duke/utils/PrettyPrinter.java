package duke.utils;

/**
 * Responsible for the formatting of output from <i>Duke</i>.
 */
public class PrettyPrinter {
    /** Number of spaces to prefix each line with. */
    private final int leftPadding;
    /** Number of underscores each divider should be made up of. */
    private final int dividerLength;

    /**
     * Constructs a new {@code PrettyPrinter} object that will prefix each line with the specified number of spaces and
     * add a divider with the specified number of underscores to the top and bottom of each message block.
     *
     * @param leftPadding the number of spaces to prefix each line with.
     * @param dividerLength the number of underscores of each divider.
     */
    public PrettyPrinter(int leftPadding, int dividerLength) {
        this.leftPadding = leftPadding;
        this.dividerLength = dividerLength;
    }

    /**
     * Prints out the number of spaces specified by {@code leftPadding}.
     */
    private void printPadding() {
        System.out.print(" ".repeat(leftPadding));
    }

    /**
     * Prints out the divider using the number of underscores specified by {@code dividerLength}. The dividers are
     * subject to left padding. If the {@code dividerLength} is {@code 0}, this method does nothing.
     */
    private void printDivider() {
        if (dividerLength != 0) {
            printPadding();
            System.out.println("_".repeat(dividerLength));
        }
    }

    /**
     * Pretty prints the message by adding left padding and dividers at the top and bottom of the message.
     *
     * @param message the string to be pretty printed.
     */
    public void print(String message) {
        // Handle both LF and CRLF
        String[] lines = message.split("\\r?\\n");
        printDivider();
        for (String line : lines) {
            printPadding();
            System.out.println(line);
        }
        printDivider();
        System.out.println();
    }
}
