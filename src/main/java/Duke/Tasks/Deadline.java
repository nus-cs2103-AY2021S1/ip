package Duke.Tasks;

import Duke.Errors.DeadlineException;
import Duke.Errors.DukeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * The deadline is a subclass of Task and it is used to describe tasks that has to be completed by a specific day.
 */
public class Deadline extends Task {
    private String day = null;
    /**
     *
     * @param name super(name) so that it does whatever is mentioned in the parent class
     * @param day assigns this.day to day value
     */
    public Deadline(String name, String day) {
        super(name);
        this.day = day;
    }

    /**
     * takes no arguments and overrides the toString method
     * @return the specific representation for deadline class as mentioned with [D] indicating that it is a deadline class
     * and also mentions the deadline.
     */
    public String toString() {
        return "[D]" + super.toString() + "(by: " + this.day + ")";
    }

    public String inputListFormat() {
        return "D" + super.inputListFormat() + " | " + this.day;
    }
    public static LocalDate localDate(String string) {
        try{
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");
            LocalDate parsedDate = LocalDate.parse(string, formatter);
            return parsedDate;
        }catch (DateTimeException d) {
            /*try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd, HH:mm");
                LocalDateTime parsedDate = LocalDateTime.parse(string, formatter);
                return parsedDate;
            } catch (DateTimeException g) {
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                    LocalTime parsedDate = LocalTime.parse(string, formatter);
                } catch (DateTimeException f) {
                    System.out.println(f.toString());
                }
            } */
            throw d;
        }
    }
    public static LocalDateTime localDateTime(String string){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd, HH:mm");
            LocalDateTime parsedDate = LocalDateTime.parse(string, formatter);
            return parsedDate;
        } catch (DateTimeException g) {
            throw g;
        }
    }
    public static LocalTime localTime(String string){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime parsedDate = LocalTime.parse(string, formatter);
            return parsedDate;
        } catch (DateTimeException f) {
            throw f;
        }
    }
    public static Deadline provide(String name, String string) throws DukeException {
        Deadline e;
        try{
            LocalDate parsedDate = localDate(string);
            e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy")));
        }catch (DateTimeException d) {
            try {
                LocalDateTime parsedDate = localDateTime(string);
                e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("dd LLL yyyy, HH:mm")));
            } catch (DateTimeException g) {
                try {
                    LocalTime parsedDate = localTime(string);
                    e = new Deadline(name, parsedDate.format(DateTimeFormatter.ofPattern("HH:mm")));
                } catch (DateTimeException f) {
                    throw new DeadlineException(false, true);
                }
            } }
        return e;

    }
}

