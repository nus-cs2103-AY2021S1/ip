package bot.task;

import bot.util.DateParser;
import bot.util.InvalidInputException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A special type of Task characterised by the input "/at" which indicates the date and time of which the task
 * should start.
 */
public class Event extends Task {
    private LocalDateTime timePeriod;

    public Event(String name, String timePeriod) throws InvalidInputException {
        super(name);
        String dateFormat = DateParser.determineDateFormat(timePeriod);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat.strip());
        this.timePeriod = LocalDateTime.parse(timePeriod, formatter);
    }

    public Event(String name, String timePeriod, boolean done) throws InvalidInputException {
        super(name, done);
        String dateFormat = DateParser.determineDateFormat(timePeriod);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(dateFormat.strip());
        this.timePeriod = LocalDateTime.parse(timePeriod.strip(), formatter);
    }

    /**
     * Serialises the object.
     *
     * @return A string that is formatted to be read and stored in Storage.
     */
    @Override
    public String toFileFormat() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return "E" + " | " + super.toFileFormat() + " | " + this.timePeriod.format(formatter)
                + "\n";
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(),
                timePeriod.format(DateTimeFormatter.ofPattern("MMM d yyyy, h:mm a")));
    }
}
