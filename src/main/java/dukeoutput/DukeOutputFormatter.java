package dukeoutput;

import constant.DukeConstants;

import java.util.List;

public class DukeOutputFormatter {

    public static String format(List<String> responses) {
        StringBuilder sb = new StringBuilder();
        sb.append(DukeConstants.LINE);
        sb.append("\n");
        responses.forEach(x -> sb.append(String.format("%s%s\n", DukeConstants.IDENT, x)));
        sb.append(DukeConstants.LINE);
        return sb.toString();
    }

    public static String format(List<String> responses, List<Integer> indentIndexes) {
        StringBuilder sb = new StringBuilder();
        sb.append(DukeConstants.LINE);
        sb.append("\n");
        int k = 0;
        for (int i = 0 ; i < responses.size() ; i++) {
            String response = responses.get(i);
            if (k < indentIndexes.size() && i == indentIndexes.get(k)) {
                sb.append(DukeConstants.IDENT);
            }
            sb.append(String.format("%s%s\n", DukeConstants.IDENT, response));
        }
        sb.append(DukeConstants.LINE);
        return sb.toString();
    }
}
