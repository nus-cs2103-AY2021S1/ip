package bot.task;

import bot.util.DateParser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



/**
 * A special type of task characterised by the input "/by" which implies the importance of the deadline.
 */
public class Deadline extends Task {
    private LocalDateTime deadline;

    public Deadline(String name, String deadline) {
        super(name);
        String dateFormat = DateParser.determineDateFormat(deadline);
        assert dateFormat != null;
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
