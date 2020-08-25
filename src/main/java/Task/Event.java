package Task;

import Exceptions.WrongDateTimeFormatException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {

    private final LocalDateTime at;

    public Event(String name, boolean isDone, String end) throws WrongDateTimeFormatException {
        super(name, isDone);
        try {
            this.at = LocalDate.parse(end.substring(0, 10)).atTime(
                    Integer.parseInt(end.substring(11, 13)),
                    Integer.parseInt(end.substring(13))
            );
        } catch (DateTimeParseException e) {
            throw new WrongDateTimeFormatException("☹ OOPS!!! Please enter the deadline time in the right format. (YYYY-MM-DD HHmm)");
        }
    }

    public Event(String name, boolean isDone, LocalDateTime at){
        super(name, isDone);
        this.at = at;
    }

    @Override
    public Task setToTrue(){
        return new Event(this.name, true, this.at);
    }

    @Override
    public String getType(){
        return "E";
    }

    @Override
    public String getEnd(){
        return this.at.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString(){
        return isDone
                ? "[E][✓] " + this.getName() + " (by: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")"
                : "[E][✗] " + this.getName() + " (by: " + this.at.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ")";
    }

    @Override
    public boolean equals(Object o){
        if (o == this){
            return true;
        } else if (o instanceof Event){
            Event temp = (Event) o;
            return this.name.equals(temp.name) && (this.isDone == temp.isDone) && this.at.equals(temp.at);
        } else {
            return false;
        }
    }
}
