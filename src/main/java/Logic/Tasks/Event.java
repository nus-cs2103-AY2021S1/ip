package Logic.Tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalTime localTime;
    protected LocalDate localDate;

    public Event(String name, LocalDate localDate, LocalTime localTime) {
        super(name);
        this.localDate = localDate;
        this.localTime = localTime;
    }

    @Override
    public String toData(){
        return "E|" + super.toData() + this.at;
    }
    
    @Override
    public String toString() {
        String date = this.localDate.format(DateTimeFormatter.ofPattern("E MMM d yyyy"));
        String time = this.localTime.format(DateTimeFormatter.ofPattern("HH mm a"));
        return "[E]" + super.toString() + " (at: " + date + " " + time + ")";
    }
}
