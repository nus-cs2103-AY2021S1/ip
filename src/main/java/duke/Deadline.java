package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Deadline extends Task {
    private LocalDate date;
    private Date time = null;
    SimpleDateFormat timeFormatter = new SimpleDateFormat("h:mm a");
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");

    public Deadline(String description) throws DukeException {
        super(description.split("/by ")[0]);
        try {
            String[] dateAndTime = description.split("/by ")[1].split(" ");
            this.date = LocalDate.parse(dateAndTime[0]);
            if (dateAndTime.length == 2) {
                SimpleDateFormat parseFormat = new SimpleDateFormat("hh:mm");
                this.time = parseFormat.parse(dateAndTime[1]);
            }
        } catch (Exception e) {
            throw new DukeException("Please input a valid date and time in the format yyyy-mm-dd HH:MM");
        }
        
    }

    public String dateAndTimeBracket() {
        if (this.time == null) {
            return String.format("(by: %s)", this.date.format(dateFormatter));
        } else {
            return String.format("(by: %s, %s)", this.date.format(dateFormatter), timeFormatter.format(this.time));
        }
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s %s",
                this.getStatusIcon(), this.description, this.dateAndTimeBracket());
    }
}
