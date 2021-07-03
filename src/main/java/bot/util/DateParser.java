package bot.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class DateParser {
    private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>();

    /**
     * Pulls the date format from dateFormat text file and load it in the class.
     *
     * @param filePath path to the dateFormat text file.
     * @throws IOException Error reading file.
     */
    public static void loadDateFormats(String filePath) throws IOException {
        InputStream in = DateParser.class.getResourceAsStream(filePath);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String content = reader.lines().collect(Collectors.joining("\n"));
        String[] items = content.split("\n");
        for (String item : items) {
            if (item.length() != 0) {
                String[] words = item.split(" \\| ");
                DATE_FORMAT_REGEXPS.put(words[0], words[1]);
            }
        }
    }

    /**
     * Decides on the dateFormat expression by matching the regex with user input.
     *
     * @param dateString human input dateFormat.
     * @return dateFormat that is accepted by date formatter.
     * @throws InvalidInputException user's dateFormat is invalid.
     */
    public static String determineDateFormat(String dateString) throws InvalidInputException {
        for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
            if (dateString.strip().toLowerCase().matches(regexp)) {
                return DATE_FORMAT_REGEXPS.get(regexp);
            }
        }
        throw new InvalidInputException("Bruh, the date format is wrong");
    }
}
