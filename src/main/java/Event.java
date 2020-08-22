import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {

    protected LocalDateTime at;

    public Event(String description, String at) {
        super(description);
        this.at = LocalDateTime.parse(at, DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"));;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (at: " + at.format(DateTimeFormatter.ofPattern("d MMM yyyy, hh:mm a")) + ")";
    }

//    public static void main(String[] args) {
//        Task event = new Event("attend wedding", "22/08/2020 2200");
//        System.out.println(event);
//    }
}
