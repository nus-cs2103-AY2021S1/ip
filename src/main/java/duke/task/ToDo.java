package duke.task;

import java.time.LocalDateTime;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String getDate() {
        return "";
    }

    @Override
    public LocalDateTime getActualDate() {
        return LocalDateTime.MAX;
    }

}
