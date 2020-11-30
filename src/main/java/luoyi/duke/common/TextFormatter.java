package luoyi.duke.common;

/**
 * Handles text formatting
 */
public class TextFormatter {
    /** Bot Logo */
    public static final String LOGO = "CAT BOT\n";

    /** Horizontal lines used for formatting */
    private static final String HORIZONTAL_LINE =
            "------------------------------------------------------------------";

    /**
     * Returns text formatted with indentation and lines.
     *
     * @param text Text to be wrapped.
     */
    public static String getFormattedText(String text) {
        String output = text.replaceAll("(?m)^", "|\t");
        if (output.charAt(output.length() - 1) != '\n') {
            output += "\n";
        }
        return HORIZONTAL_LINE + "\n" + output + HORIZONTAL_LINE + "\n";
    }
}
