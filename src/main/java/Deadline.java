import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Deadline extends Task {
    //dealing with dates
    LocalDate deadlineDate;
    LocalTime deadlineTime;

    public Deadline(String description, String deadline) {
        super(description);
        String[] dateTimeSplit = deadline.split(" ");
        String[] dateSplit = dateTimeSplit[1].split("/");
        if (dateSplit[0].length() == 4) {
            int year = Integer.parseInt(dateSplit[0]);
            int month = Integer.parseInt(dateSplit[1]);
            int day = Integer.parseInt(dateSplit[2]);
            assert (year >= 2020 && month > 0 && month <= 12 && day > 0 && day <= 31) : "Is not a valid date";
            deadlineDate = LocalDate.of(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2]));
        } else {
            int year = Integer.parseInt(dateSplit[2]);
            int month = Integer.parseInt(dateSplit[1]);
            int day = Integer.parseInt(dateSplit[0]);
            assert (year >= 2020 && month > 0 && month <= 12 && day > 0 && day <= 31) : "Invalid date input";
            deadlineDate = LocalDate.of(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[0]));
        }
        int hour = Integer.parseInt(dateTimeSplit[2].substring(0,2));
        int minute = Integer.parseInt(dateTimeSplit[2].substring(2));
        assert hour >= 0 && hour < 24 && minute >= 0 && minute < 60 : "Invalid time input";
        deadlineTime = LocalTime.of(hour, minute);
    }

    public Deadline(String description, String deadline, boolean isDone) {
        super(description);
        String[] dateTimeSplit = deadline.split(" ");
        String[] dateSplit = dateTimeSplit[0].split("/");

        if (dateSplit[0].length() == 4) {
            deadlineDate = LocalDate.of(Integer.parseInt(dateSplit[0]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[2]));
        } else {
            deadlineDate = LocalDate.of(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[1]), Integer.parseInt(dateSplit[0]));
        }
        deadlineTime = LocalTime.of(Integer.parseInt(dateTimeSplit[1].substring(0,2)), Integer.parseInt(dateTimeSplit[1].substring(2)));
        this.isDone = isDone;
    }


    @Override
    public String getDescription() {
        return super.getDescription() + " ~ " + deadlineDate.toString().replaceAll("-", "/") + " " + deadlineTime.toString().replace(":","");
    }

    @Override
    public String getStatusIcon() {
        return (isDone ? "[\u2713]" : "[\u2718]"); //return tick or X symbols
    }

    @Override
    public String toString() {
        return "[D]" + super.getStatusIcon() + this.description + " (by:" + deadlineDate.toString() + " " + deadlineTime.toString() + ")";
    }
}
