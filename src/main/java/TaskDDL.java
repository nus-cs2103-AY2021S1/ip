import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TaskDDL extends Task {

    LocalDate ddl;

    public TaskDDL(String task, LocalDate ddl) {
        super(task);
        this.ddl = ddl;
    }

    protected String getDateTime() {
        return ddl.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}
