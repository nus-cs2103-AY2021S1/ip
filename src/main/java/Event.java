import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

//TODO: write in the time for the toString
public class Event extends Task{
    String preposition;
    LocalDate date;
    LocalTime time;
    Event(String title, String preposition, LocalDate date, LocalTime time) throws WrongUsageException{
        super(title);
        this.name = "event";
        this.usage = "event [EventName] ['/on' OR '/at'] [DD/MM/YYYY] [HH:MM]";
        this.description = "Stores a task in the list marked as an event";
        if(title.isEmpty() || preposition.isEmpty()){
            throw new WrongUsageException(this.name, this.usage);
        }
        this.preposition = preposition;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() + "(" + preposition + ": " +
                date.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + " " +
                time.format(DateTimeFormatter.ofPattern("HH:mm")) + ")";
    }


}
