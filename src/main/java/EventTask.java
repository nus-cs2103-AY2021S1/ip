import java.time.LocalDateTime;

public class EventTask extends Task {

    private static String display = "[E]";
    private final LocalDateTime date;

    EventTask(String name, LocalDateTime date) {
        super(name);
        this.date = date;
    }

    @Override
    public String toString() {
        return display + super.toString() + " (at: " + date + ")";
    }

    @Override
    public String saveString() {return "E/break/" + this.done + "/break/" + name + "/break/" + date;}
}
