package dukechatbot.dukeoutput;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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
        assert(!Objects.isNull(response));
        return DukeOutputFormatter.format(Collections.singletonList(response));
    }

    /**
     * Formats and prints the responses from Duke.
     *
     * @param responses list of responses by Duke.
     */
    public static String output(List<String> responses) {
        assert(!Objects.isNull(responses));
        return DukeOutputFormatter.format(responses);
    }

    /**
     * Formats and prints the responses from Duke.
     * 
     * @param responses list of responses by Duke.
     * @param indentIndexes to indicate which response is formatted more.
     */
    public static String output(List<String> responses, List<Integer> indentIndexes) {
        assert(!Objects.isNull(responses));
        assert(!Objects.isNull(indentIndexes));
        return DukeOutputFormatter.format(responses, indentIndexes);
    }
}
