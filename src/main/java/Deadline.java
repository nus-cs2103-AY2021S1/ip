package main.java;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDate by;


    public Deadline(String description, String str) {
        super(description);
        this.by = LocalDate.parse(str);
    }

    public String getBy(){
        return by;
        
    }

    @Override
    public String toString() {

        return "[D]" + super.toString() + "(by: "
                + this.by.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ")";

    }


}