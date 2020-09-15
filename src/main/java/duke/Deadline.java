package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    String dueDate;
    LocalDate localDate;

    public Deadline(String name, Status status, String dueDate) {
        super(name, status);
        this.dueDate = dueDate;
        //System.out.println(dueDate);
        this.localDate = LocalDate.parse(dueDate);
    }

    @Override public String toString() {

        return "[D] " + this.status.statusToSymbol() + this.name +
                " by: " +
                localDate.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy"));

    }

    @Override public String toStore() {

        return "[D] " + this.status.statusToSymbol() + this.name +
                " by: " + dueDate;

    }

}
