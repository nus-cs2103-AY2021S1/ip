package main.java;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventsTask extends Task {
    protected String stringPeriod;
    protected LocalDateTime period;

    public EventsTask(String period, String task, TaskSymbol taskType){
        super(task, taskType);
        this.stringPeriod = period;
        this.period = LocalDateTime.parse(period, DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    @Override
    public String toString() {

        return super.toString() + "(at: " + period.format(DateTimeFormatter.ofPattern("dd MMM yyyy hh:mm a")) + ")";
    }

}
