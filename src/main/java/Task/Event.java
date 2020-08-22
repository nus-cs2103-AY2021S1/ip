package Task;

import Helper.DateTimeHelper;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate time;
    private String exactTime;
    public Event(String content, LocalDate time, String exactTime) {
        super(content);
        this.time = time;
        this.exactTime = exactTime;
    }
    @Override
    public String returnStringForm() {
        return "[E]" + super.returnStringForm() + "( at: " + DateTimeHelper.getStringRep(time) + " " + this.exactTime + ")";
    }
}
