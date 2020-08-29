package duke;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Events extends Task{
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    Events(String name, String time) {
        super(name,time);
        String[] at = time.split(" ");
        this.date = parseDate(at[1]);
        String[] timeArray = at[2].split("-");
        this.startTime = parseTime(timeArray[0]);
        this.endTime = parseTime(timeArray[1]);
    }

    public LocalDate parseDate(String dateString) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, format);
    }

    public LocalTime parseTime(String timeString) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("HHmm");
        return LocalTime.parse(timeString, format);
    }

    public String printDateTime() {
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd MMM yyyy");
        DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("h:mma");
        return String.format("%s, %s to %s",
                formatDate.format(date),
                formatTime.format(startTime),
                formatTime.format(endTime));

    }

    @Override
    public String toString(){
        if (super.isDone) {
            return "[E]" + "[" + "✓" + "] " + super.getName() + "(at: " + printDateTime()  +")";
        } else {
            return "[E]" + "[" + "✗" + "] " + super.getName() + "(at: " + printDateTime()  +")";
        }

    }

}
