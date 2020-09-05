package dukechatbot.dukeoutput;

import java.util.Collections;
import java.util.List;

/**
 * Represents the class to output the response of Duke.
 */
public class DukeOutput {
    
    /**
     * Formats and prints the response from Duke.
     *
     * @param response from Duke.
     */
    public static String output(String response) {
        return DukeOutputFormatter.format(Collections.singletonList(response));
    }

    /**
     * Formats and prints the responses from Duke.
     *
     * @param response list of responses by Duke.
     */
    public static String output(List<String> response) {
        return DukeOutputFormatter.format(response);
    }

    /**
     * Formats and prints the responses from Duke.
     * 
     * @param responses list of responses by Duke.
     * @param indentIndexes to indicate which response is formatted more.
     */
    public static String output(List<String> responses, List<Integer> indentIndexes) {
        return DukeOutputFormatter.format(responses, indentIndexes);
    }
}
