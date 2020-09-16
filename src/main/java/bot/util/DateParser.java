package bot.util;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class DateParser {
    private static final Map<String, String> DATE_FORMAT_REGEXPS = new HashMap<String, String>();

    public static void loadDateFormats(String filePath) throws IOException {
        String content = Files.readString(Paths.get(filePath), StandardCharsets.UTF_8);
        String[] items = content.split("\n");
        for (String item : items) {
            if (item.length() != 0) {
                String[] words = item.split(" \\| ");
                DATE_FORMAT_REGEXPS.put(words[0], words[1]);
            }
        }
    }

    public static String determineDateFormat(String dateString) throws InvalidInputException {
        for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
            if (dateString.strip().toLowerCase().matches(regexp)) {
                return DATE_FORMAT_REGEXPS.get(regexp);
            }
        }
        throw new InvalidInputException("Bruh, the date format is wrong");
    }
}
