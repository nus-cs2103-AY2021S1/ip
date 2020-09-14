package olivia.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class DatedTask extends Task {

    private static final String DESCRIPTION = "/description";
    private static final String TIME = "/time";

    protected LocalDateTime time;

    protected DatedTask(String description, String tag, String time, boolean isDone) {
        super(description, "E", isDone);
        this.time = LocalDateTime.parse(time,
                DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
    }

    public void update(String str, String field) {
        switch (field) {
        case DESCRIPTION:
            super.update(str);
            break;
        case TIME:
            this.time = LocalDateTime.parse(str,
                    DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm"));
            break;
        default:
            break;
        }
    }

}
