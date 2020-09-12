package clippy.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String desc, String byString) throws DateTimeParseException {
        super(desc);
        this.by = LocalDate.parse(byString);
        taskType = TaskType.DEADLINE;
    }
    
    public void updateTime(String newTime) {
        this.by = LocalDate.parse(newTime);
    }

    @Override
    public String toString() {
        int day = by.getDayOfMonth();
        String month = by.getMonth().getDisplayName(TextStyle.SHORT_STANDALONE, Locale.ENGLISH);
        int year = by.getYear();
        return "[" + taskType + "]" + super.toString() + " (by: " 
                + month + " " + day + " " + year + ")";
    }

    @Override
    public String generateSaveFileData() {
        return "D|" + (isDone ? "1" : "0") + "|" + desc + "|" + by;
    }

}
