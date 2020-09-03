package duke;

public class Formatter {
    /**
     * format_response takes any output message and format it into Duke's
     * response format.
     * @param outputMsg output message to be printed
     * @return formatted message
     */
    public static String formatResponse(String outputMsg) {
        return 
            "____________________________________________________________\n"
            + outputMsg
            + "____________________________________________________________\n";
    }
}
