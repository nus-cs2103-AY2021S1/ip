import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate date;
    private LocalTime time;
    private String dateTimeTxt;

    public Deadline(String description, String dateTime) throws DukeInvalidUserInputException {
        super(description);
        convertDateTime(dateTime);
    }

    private void convertDateTime(String dateTime) throws DukeInvalidUserInputException {
        try {
            String[] dateTimeArr = dateTime.split(" ");
            this.date = LocalDate.parse(dateTimeArr[0]);
            this.time = LocalTime.parse(dateTimeArr[1].substring(0, 2) + ":" + dateTimeArr[1].substring(2, 4));
            this.dateTimeTxt = this.date.format(DateTimeFormatter.ofPattern("d MMMM yyyy")) +
                    " " + this.time.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (DateTimeParseException e) {
            throw new DukeInvalidUserInputException("It seems you have entered an invalid date and time. " +
                    "The format should be as follows YYYY-MM-DD hhmm.");
        }
    }

    public String toTxtFormat() {
        return "D | " + super.toTxtFormat() + " | " + this.dateTime;
    }

    public static Deadline parse(String txtFormat, String[] txtArray) {
        Deadline deadline = new Deadline(txtArray[2].trim(), txtArray[3].trim());
        if (txtArray[1].trim().equals("1")) {
            deadline.markAsDone();
        }
        return deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTimeTxt + ")";
    }
}
