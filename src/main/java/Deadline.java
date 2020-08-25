import java.time.LocalDateTime;

/**
 * Deadline class creates a type of Task called deadline which contains a date and description of the task
 */
public class Deadline extends Task {

   // protected String by;
    private LocalDateTime date;

    public Deadline(String description, LocalDateTime date) {
        super(description);
        this.date = date;
    }

    /**
     * creates a deadline a string
     * @param str input form user
     * @return Deadline object
     */
    public static Deadline createDeadline(String str) {
        if (str == null) {
            throw new DukeException("I need something to work with.");
        }
        String[] detailsArray = str.split("/by", 2);
        try {
            String description = detailsArray[0].trim();
            String dateTimeString = detailsArray[1].trim();
            LocalDateTime dateTime = DateConverter.parseString(dateTimeString);
            return new Deadline(description, dateTime);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("No date found");
        }
    }

    /**
     * convertes a String into a condensed form
     * @return condesnsed form of inputted string
     */
    public String encode() {
        return String.format("D|%s|%s|%s", super.isDone ? "Y" : "N", this.date, super.description);
    }

    /**
     * unravels encoded Strings
     * @param code String that has been previously encoded()
     * @return Deadline object
     * @throws DukeException in the event it is unable to decode the string
     */
    public static Deadline decode(String code) throws DukeException {
        if (code.charAt(0) == 'D') {
            String[] content = code.split("\\|", 4);

            if (content.length != 4) {
                throw new Error("data string is not equal to 4");
            }

            Deadline newDeadline = new Deadline(content[3], DateConverter.parseString(content[2]));
            if (content[1].equals("Y")) {
                newDeadline.markAsDone();
            }
            return newDeadline;
        } else {
            throw new DukeException("Unable to decode Deadline");
        }
    }


    /**
     * overrides Deadline String output to be formatted
     * @return String of formatted Deadline
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + "(by:" + DateConverter.parseLocalDateTime(this.date) + ")";
    }
}