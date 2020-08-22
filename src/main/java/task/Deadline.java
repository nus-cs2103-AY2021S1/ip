package task;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Deadline extends Task {

    private String deadline;

    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public Deadline(boolean completed, String description, String deadline) {
        super(completed, description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }

    @Override
    public String toCSV() {
        return "D," + super.toCSV() + "," + deadline;
    }

    @Override
    public Task fromCSV(String csv) {
        Scanner scanner = new Scanner(csv);
        Pattern pattern = Pattern.compile("([^,]+?),");
        return new Deadline(scanner.nextBoolean(), scanner.next(pattern), scanner.next(pattern));
    }
}
