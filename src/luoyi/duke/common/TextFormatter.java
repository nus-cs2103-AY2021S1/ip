package luoyi.duke.common;

/**
 * Handles text formatting
 */
public class TextFormatter {
    /** Horizontal lines used for formatting */
    private static final String HORIZONTAL_LINE = "∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴‥∵‥∴‥∴‥∵‥∴";
    private static final String HORIZONTAL_LINE_2 = "▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼△▼";

    /** Bot Logo */
    public static final String LOGO =
            "　　＼　　　　　／\n"
            + "　　　＼∧∧∧／\n"
            + "　　　＜　 Ｃ ＞\n"
            + "　　　＜ 　Ａ ＞\n"
            + "　　　＜　 Ｔ ＞\n"
            + "　 ───＜ Ｂ　 ＞───\n"
            + "　　　＜ Ｏ　 ＞\n"
            + "　　　＜ Ｔ　 ＞\n"
            + "　　　／∨∨∨＼\n"
            + "　　／ ∧_∧　　＼\n"
            + "　／　( ･ω･)　　 ＼\n"
            + "／　＿(_つ/￣￣￣/　＼\n"
            + "　　 　＼/＿＿＿/\n";

    /**
     * Returns text formatted with indentation and lines.
     *
     * @param text Text to be wrapped.
     */
    public static String getFormattedText(String text) {
        String output = text.replaceAll("(?m)^", "∴‥\t");
        if (output.charAt(output.length() - 1) != '\n') {
            output += "\n";
        }
        return HORIZONTAL_LINE + "\n" + output + HORIZONTAL_LINE + "\n";
    }
}
