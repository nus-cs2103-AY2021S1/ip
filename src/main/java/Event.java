import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String dateTimeTxt;

    public Event(String description, String dateTime) throws DukeInvalidUserInputException{
        super(description);
        convertDateTime(dateTime);
    }

    private void convertDateTime(String dateTime) throws DukeInvalidUserInputException {
        try {
            String[] dateTimeArr = dateTime.split(" ");
            this.date = LocalDate.parse(dateTimeArr[0]);
            String []timeArr = dateTimeArr[1].split("-");
            this.startTime = LocalTime.parse(timeArr[0].substring(0, 2) + ":" + timeArr[0].substring(2, 4));
            this.endTime = LocalTime.parse(timeArr[1].substring(0, 2) + ":" + timeArr[1].substring(2, 4));
            this.dateTimeTxt = this.date.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) +
                    " " + this.startTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + " to " +
                    this.endTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeInvalidUserInputException("It seems you have entered an invalid date and time. The format should be as follows YYYY-MM-DD hhmm-hhmm.");
        }
    }

    public String toTxtFormat() {
        return "E | " + super.toTxtFormat() + " | " + this.dateTime;
    }

    public static Event parse(String txtFormat, String[] txtArray) {
        Event event = new Event(txtArray[2].trim(), txtArray[3].trim());
        if (txtArray[1].trim().equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTimeTxt + ")";
    }
}
