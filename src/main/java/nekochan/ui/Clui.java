package nekochan.ui;

import java.util.Scanner;

import nekochan.util.Messages;

/**
 * The {@code Clui} class manages program input and output.
 */
public class Clui {

    private static final String DIVIDER = "________________________________________________________\n";

    private Scanner sc;

    /**
     * Constructs a {@code Clui} instance.
     */
    public Clui() {
        sc = new Scanner(System.in);
    }

    /**
     * Returns a string containing the specified content where each line is
     * indented by the specified indent. The returned string always contains a newline character at the end.
     * <p>
     * If provided a negative indent, an indent of 0 is applied.
     *
     * @param content the content to indent.
     * @param indent  the amount of spaces to indent by.
     * @return a string containing the specified content where each line is indented by the specified indent.
     */
    private String prependIndent(String content, int indent) {
        String spaces = "";
        for (int i = 0; i < indent; i++) {
            spaces += " ";
        }
        String[] contentArray = content.split("\n");
        String result = "";
        for (String s : contentArray) {
            result += spaces + s + "\n";
        }
        return result;
    }

    /**
     * Formats and prints the specified content.
     *
     * @param content the content to be printed.
     */
    public void print(String content) {
        System.out.print(prependIndent(DIVIDER, 4));
        System.out.print(prependIndent(content, 5));
        System.out.println(prependIndent(DIVIDER, 4));
    }

    /**
     * Prints the default welcome message.
     */
    public void greet() {
        System.out.print(prependIndent(DIVIDER, 4));
        System.out.print(prependIndent(Messages.MESSAGE_WELCOME, 5));
        System.out.println(prependIndent(DIVIDER, 4));
    }

    /**
     * Reads in user input until the next line separator.
     *
     * @return the user input that was read.
     */
    public String readCommand() {
        return sc.nextLine();
    }
}
