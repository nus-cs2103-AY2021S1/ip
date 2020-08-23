package duke;

import java.time.LocalDateTime;

public class EventStub extends TimedTask {

    public EventStub(String description) {
        super(description, "9-08-2020 7:00"); // dummy datetime string
    }

    @Override
    public String toString() {
        String[] arr = description.split("/at", 2);
        arr[1] = LocalDateTime.parse(arr[1].trim(), EventStub.inputFormatter).format(EventStub.printFormatter);
        return "[E]" + "[✗]" + " " + arr[0].trim() + " (at: " + arr[1].trim() + ")";

    }
}
