package duke.tasks;

import duke.exceptions.DukeException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    private LocalDate date;
    private String time;

    public Event(String description, LocalDate date,String time) throws DukeException {
        super(description.substring(6),"event");
//        this.dateTime = description.substring(description.indexOf("/")+4);
        this.date = date;
        this.time = time;
        this.setType("event");

    }

    @Override
    public String toString() {
        return "[E]" + super.toString()+" (at: " + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))+" "+time + ")";
    }

    public String toSave(){
        return "[E]" + super.toString()+" (at: " + date+" "+time + ")";
    }


}
