import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {
    protected LocalDate by;

    public Deadline(String desc, String byString) {
        super(desc);
        this.by = LocalDate.parse(byString);
        taskType = TaskType.DEADLINE;
    }

    @Override
    public String toString() {
        return "[" + taskType + "]" + super.toString() + " (by: " 
                + by.getMonth().getDisplayName(TextStyle.SHORT_STANDALONE, Locale.ENGLISH) + " " 
                + by.getDayOfMonth() + " " 
                + by.getYear() + ")";
    }

    @Override
    public String generateSaveFileData() {
        return "D|" + (isDone ? "1" : "0") + "|" + desc + "|" + by;
    }

}
