package duke;

import java.time.LocalDateTime;

/** Stub for Deadline class, for JUnit testing purposes.
 */
public class DeadlineStub extends TimedTask {

    public DeadlineStub(String description) {
        super(description, "9-08-2020 9:00"); // dummy datetime string
    }

    @Override
    public String toString() {
        String[] arr = description.split("/by", 2);
        arr[1] = LocalDateTime.parse(arr[1].trim(), DeadlineStub.INPUT_FORMAT).format(DeadlineStub.PRINT_FORMAT);
        return "[D]" + "[✗]" + " " + arr[0].trim() + " (by: " + arr[1].trim() + ")";
    }
}
