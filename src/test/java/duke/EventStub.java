package duke;

import java.time.LocalDateTime;

/** Stub for Event class, for JUnit testing purposes.
 */
public class EventStub extends TimedTask {

    public EventStub(String description) {
        super(description, "9-08-2020 7:00"); // dummy datetime string
    }

    @Override
    public String toString() {
        String[] arr = description.split("/at", 2);
        arr[1] = LocalDateTime.parse(arr[1].trim(), EventStub.INPUT_FORMAT).format(EventStub.PRINT_FORMAT);
        return "[E]" + "[✗]" + " " + arr[0].trim() + " (at: " + arr[1].trim() + ")";

    }
}
