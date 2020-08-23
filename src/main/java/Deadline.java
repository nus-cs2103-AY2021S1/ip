import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    protected boolean hasTime = true;
    protected LocalDate time;

    public Deadline(String description, LocalDate time) {
        super(description);
        this.time = time;
    }

    public LocalDate getTime() {
        return time;
    }

    public boolean getHasTime(){
        return hasTime;
    }


    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy");
        String timeString = time.format(formatter);
        return super.toString().replace("[\u2718]", "[D][\u2718]") + " (by: " + timeString + ")";
    }

    @Override
    public String deleteMessage() {
        return super.deleteMessage().replace("[\u2718]", "[T][\u2718]");
    }
}
