package dukechatbot.dukeoutput;

import java.util.List;

import dukechatbot.constant.DukeConstants;
/**
 * Represents class to format
 * the response to be outputted to Duke.
 */
class DukeOutputFormatter {

    /**
     * Formats and prints the responses from Duke.
     * 
     * @param responses
     * @return Formatted responses from Duke
     */
    static String format(List<String> responses) {
        StringBuilder sb = new StringBuilder();
        sb.append(DukeConstants.LINE);
        sb.append("\n");
        responses.forEach(x -> sb.append(
                String.format("%s%s\n", DukeConstants.INDENT, x)));
        sb.append(DukeConstants.LINE);
        return sb.toString();
    }

    /**
     * Formats and prints the responses from Duke.
     * 
     * @param responses
     * @param indentIndexes
     * @return Formatted responses from Duke
     */
    static String format(List<String> responses, List<Integer> indentIndexes) {
        StringBuilder sb = new StringBuilder();
        sb.append(DukeConstants.LINE);
        sb.append("\n");
        int k = 0;
        for (int i = 0; i < responses.size(); i++) {
            String response = responses.get(i);
            if (k < indentIndexes.size() && i == indentIndexes.get(k)) {
                sb.append(DukeConstants.INDENT);
            }
            sb.append(String.format("%s%s\n", DukeConstants.INDENT, response));
        }
        sb.append(DukeConstants.LINE);
        return sb.toString();
    }
}
