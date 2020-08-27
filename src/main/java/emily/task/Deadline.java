package main.java.emily.task;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task which has a timestamp by a given date
 * and a description detail
 */
public class Deadline extends Task {

    protected LocalDate by;
    protected String str;


    public Deadline(String description, String str) {
        super(description);
        this.str = str;
        this.by = LocalDate.parse(str);
    }

    /**
     * Getter method to retrieve timestamp
     * @return timestamp in the format yyy-mm--dd
     */
    public String getBy(){
        return this.str;
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + "(by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";

    }


}