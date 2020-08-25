package duke.task;

import java.time.LocalDateTime;
import duke.exceptions.DukeException;
import duke.parser.DateParser;

public class Deadline extends Task {

    private static final String DEADLINE_DELIMITER = "/by";

    private LocalDateTime dateTime;

    private Deadline(String description, LocalDateTime dateTime) {
        super(description);
        this.dateTime = dateTime;
    }

    public static Deadline createTask(String details) {
        if (details == null) {
            throw new DukeException("I need something to work with.");
        }
        String[] detailsArray = details.split(DEADLINE_DELIMITER, 2);
        try {
            String description = detailsArray[0].trim();
            String dateTimeString = detailsArray[1].trim();
            LocalDateTime dateTime = DateParser.parseString(dateTimeString);
            return new Deadline(description, dateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("So you never did plan on doing it huh...");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + DateParser.parseLocalDateTime(this.dateTime) + ")";
    }

    public String encode() {
        return String.format("D|%s|%s|%s", super.completed ? "Y" : "N", DateParser.parseLocalDateTime(this.dateTime), super.description);
    }

    public static Deadline decode(String code) throws DukeException {
        if (code.charAt(0) == 'D') {
            String[] content = code.split("\\|", 4);
            if (content.length != 4) {
                throw new Error("Your data is corrupt.");
            }
            Deadline newDeadline = new Deadline(content[3], DateParser.parseString(content[2]));
            if (content[1].equals("Y")) {
                newDeadline.setCompleted();
            }
            return newDeadline;
        } else {
            throw new DukeException("Unable to decode duke.task.Deadline.");
        }
    }
}
