//@@author {FooJingYi}-reused
//Reused from https://nus-cs2103-ay2021s1.github.io/website/schedule/week2/project.html with minor modifications

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {

    protected String dl; //deadline given
    protected LocalDate date;
    protected LocalTime time;

    public Deadline(String taskName, String dl) throws WrongDeadlineException {
        super(taskName);
        this.dl = dl;
        String[] temp = dl.split(" ", 2);
        try {
            this.date = LocalDate.parse(temp[0]);
            this.time = LocalTime.parse(temp[1]);
        } catch (DateTimeParseException e) {
            throw new WrongDeadlineException("deadline", "/by");
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " by: " +
                this.date.format(DateTimeFormatter.ofPattern("d MMM yyyy")) +
                ", " +
                this.time.format(DateTimeFormatter.ofPattern("h.m a"));
    }
}