package task;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Event extends Task {

    private String eventTime;

    public Event(String description, String eventTime) {
        super(description);
        this.eventTime = eventTime;
    }

    public Event(boolean completed, String description, String eventTime) {
        super(completed, description);
        this.eventTime = eventTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + eventTime + ")";
    }

    @Override
    public String toCSV() {
        return "E," + super.toCSV() + "," + eventTime;
    }

    @Override
    public Task fromCSV(String csv) {
        Scanner scanner = new Scanner(csv);
        Pattern pattern = Pattern.compile("([^,]+?),");
        return new Event(scanner.nextBoolean(), scanner.next(pattern), scanner.next(pattern));
    }

}
