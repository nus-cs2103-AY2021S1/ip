package duke;

public class Formatter {
    /**
     * format_response takes any output message and format it into Duke's
     * response format.
     * @param output_msg output message to be printed
     * @return formatted message
     */
    public static String formatResponse(String output_msg) {
        return 
        "____________________________________________________________\n"+
        output_msg +
        "____________________________________________________________\n";
    }
}
