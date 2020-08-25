package data.task;

import data.exception.DukeInvalidUserInputException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private String dateTimeTxt;

    public Event(String description, String dateTime) throws DukeInvalidUserInputException {
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
            this.dateTimeTxt = this.date.format(DateTimeFormatter.ofPattern("d MMMM yyyy"))
                    + " " + this.startTime.format(DateTimeFormatter.ofPattern("hh:mm a")) + " to "
                    + this.endTime.format(DateTimeFormatter.ofPattern("hh:mm a"));
        } catch (DateTimeParseException | IndexOutOfBoundsException e) {
            throw new DukeInvalidUserInputException("It seems you have entered an invalid date and time."
                    + " The format should be as follows YYYY-MM-DD hhmm-hhmm.");
        }
    }

    public String toTxtFormat() {
        return "E | " + super.toTxtFormat() + " | " + this.dateTimeTxt;
    }

    public static Event parse(String[] txtArray) throws DukeInvalidUserInputException {
        String done = txtArray[1].trim();
        String description = txtArray[2].trim();
        String[] unFormattedDateTime = txtArray[3].trim().split(" ");

        String unformattedDate
                = unFormattedDateTime[0] + " " + unFormattedDateTime[1] + " " + unFormattedDateTime[2];
        String unformattedStartTime
                = unFormattedDateTime[3] + " " + unFormattedDateTime[4];
        String unformattedEndTime
                = unFormattedDateTime[6] + " " + unFormattedDateTime[7];

        String formattedDate
                = LocalDate.parse(unformattedDate, DateTimeFormatter.ofPattern("d MMMM yyyy")).toString();
        String startLocalTime
                = LocalTime.parse(unformattedStartTime, DateTimeFormatter.ofPattern("hh:mm a")).toString();
        String endLocalTime
                = LocalTime.parse(unformattedEndTime, DateTimeFormatter.ofPattern("hh:mm a")).toString();

        String formattedStartTime = startLocalTime.substring(0, startLocalTime.indexOf(':'))
                + startLocalTime.substring(startLocalTime.indexOf(':') + 1);
        String formattedEndTime = endLocalTime.substring(0, endLocalTime.indexOf(':'))
                + endLocalTime.substring(endLocalTime.indexOf(':') + 1);

        String finalDateTime = formattedDate + " " + formattedStartTime + "-" + formattedEndTime;
        Event event = new Event(description, finalDateTime);
        if (done.equals("1")) {
            event.markAsDone();
        }
        return event;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + this.dateTimeTxt + ")";
    }
}
