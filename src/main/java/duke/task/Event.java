package duke.task;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    final LocalDate atDate;
    final LocalTime atTime;

    public Event(String description, LocalDate atDate, LocalTime atTime) {
        super(description, TaskType.EVENT);
        this.atDate = atDate;
        this.atTime = atTime;
    }

    public LocalDate getDate() {
        return atDate;
    }

    public String getAt() { //"21/08/2020 1900" eg
        String time = atTime == null
                ? ""
                : " " + atTime.format(DateTimeFormatter.ofPattern("HHmm"));
        return atDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + time;
    }

    @Override
    public String toString() {
        String time = atTime == null
                        ? ""
                : ", " + atTime.format(DateTimeFormatter.ofPattern("h.mma"));
        //special display of date and time
        return super.toString()
                + " (at: " + atDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"))
                + time + ")";
    }
}