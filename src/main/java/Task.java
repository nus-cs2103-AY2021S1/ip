import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

abstract class Task {
    protected String taskType;
    protected String description;
    protected boolean isDone;

    Task(String tasktype, String description) {
        this.taskType = tasktype;
        this.description = description;
        this.isDone = false;
    }

    protected boolean containsTime(String str) {
        Pattern p = Pattern.compile(".*([01]?[0-9]|2[0-3])[0-5][0-9]");
        Matcher m = p.matcher(str);
        return m.matches();
    }

    protected String formatDate(String str) {
        LocalDate ld = LocalDate.parse(str);
        return ld.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }

    protected String formatDateTime(String str) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy HHmm");
        LocalDateTime ldt = LocalDateTime.parse(str, formatter);
        return ldt.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm"));
    }

    protected void completeTask() {
        this.isDone = true;
    }

    protected String getStatusIcon() {
        return this.isDone ? "[✓] " : "[✗] ";
    }

    abstract public String formatTaskForFile();

    @Override
    public String toString() {
        return this.getStatusIcon() + this.description;
    }
}
