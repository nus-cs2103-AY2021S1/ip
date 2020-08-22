package Task;

import Helper.DateTimeHelper;

import java.time.LocalDate;

public class Event extends Task {
    private LocalDate time;
    private String exactTime;
    private String deadlineStr;
    public Event(String content, LocalDate time, String exactTime, String deadlineStr) {
        super(content);
        this.time = time;
        this.exactTime = exactTime;
        this.deadlineStr = deadlineStr;
    }

    public LocalDate getTime() {
        return time;
    }
    public String getDealineStr() {
        return this.deadlineStr;
    }
    @Override
    public String returnStringForm() {
        return "[E]" + super.returnStringForm() + "( at: " + DateTimeHelper.getStringRep(time) + " " + this.exactTime + ")";
    }
}
