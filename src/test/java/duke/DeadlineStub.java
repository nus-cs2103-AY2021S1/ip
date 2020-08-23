package duke;

import java.time.LocalDateTime;

public class DeadlineStub extends TimedTask {

    public DeadlineStub(String description) {
        super(description, "9-08-2020 9:00"); // dummy datetime string
    }

    @Override
    public String toString() {
        String[] arr = description.split("/by", 2);
        arr[1] = LocalDateTime.parse(arr[1].trim(), DeadlineStub.inputFormatter).format(DeadlineStub.printFormatter);
        return "[D]" + "[✗]" + " " + arr[0].trim() + " (by: " + arr[1].trim() + ")";
    }
}
