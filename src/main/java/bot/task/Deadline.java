package bot.task;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * A special type of task characterised by the input "/by" which implies the importance of the deadline.
 */
public class Deadline extends Task {
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

    private LocalDateTime deadline;

    public String determineDateFormat(String dateString) {
        for (String regexp : DATE_FORMAT_REGEXPS.keySet()) {
            if (dateString.strip().toLowerCase().matches(regexp)) {
                return DATE_FORMAT_REGEXPS.get(regexp);
            }
        }
        return null; // Unknown format.
    }

    public Deadline(String name, String deadline) {
        super(name);
        String dateFormat = determineDateFormat(deadline);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat.strip());
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    public Deadline(String name, String deadline, boolean done) {
        super(name, done);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        this.deadline = LocalDateTime.parse(deadline, formatter);
    }

    /**
     * Serialises the object.
     * @return A string that is formatted to be read and stored in Storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "D" + " | " + super.toFileFormat() + " | " + this.deadline.format(formatter)
                + "\n";
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(),
                deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
    }
}
