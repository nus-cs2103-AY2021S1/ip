import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected String at;
    protected LocalDate date;
    protected LocalTime time;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        String[] timeComponent = at.split(" ");
        this.date = LocalDate.parse(format(timeComponent[0].trim()));
        String hour = timeComponent[1].substring(0,2);
        String minute = timeComponent[1].substring(2);
        this.time = LocalTime.of(Integer.parseInt(hour),
                Integer.parseInt(minute));
    }

    private String format(String input) {
        String[] component = input.split("/");
        if (component[0].length() == 1) {
            component[0] = "0" + component[0];
        }
        return component[2] + "-" + component[1] +
                "-" + component[0];
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + date.format(DateTimeFormatter
                .ofPattern("MMM d yyyy")) + " " + time + ")";
    }

    @Override
    public String getDate() {
        return this.at.split(" ")[0];
    }

    @Override
    public String convertTxt() {
        return "E | " + (this.status ? "1" : "0") + " | " + name + " | " + at;
    }
}
