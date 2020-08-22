package task;

import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Todo is a Task with no defined datetime
 */
public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public ToDo(boolean completed, String description) {
        super(completed, description);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toCSV() {
        return "T," + super.toCSV();
    }

    @Override
    public Task fromCSV(String csv) {
        Scanner scanner = new Scanner(csv);
        Pattern pattern = Pattern.compile("([^,]+?),");
        return new ToDo(scanner.nextBoolean(), scanner.next(pattern));
    }
}
