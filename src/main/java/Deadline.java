import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    final LocalDate byDate;
    final LocalTime byTime;

    Deadline(String description, LocalDate byDate, LocalTime byTime) {
        super(description, TaskType.DEADLINE);
        this.byDate = byDate;
        this.byTime = byTime;
    }

    public LocalDate getDate() {
        return byDate;
    }

    public String getBy() { //"21/08/2020 1900" eg
        String time = byTime == null
                ? ""
                : " " + byTime.format(DateTimeFormatter.ofPattern("HHmm"));
        return byDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + time;
    }

    @Override
    public String toString() {
        String time = byTime == null
                ? ""
                : ", " + byTime.format(DateTimeFormatter.ofPattern("h.mma"));
        //special display of date and time
        return super.toString()
                + " (by: " + byDate.format(DateTimeFormatter.ofPattern("MMM-dd-yyyy"))
                + time + ")";
    }
}