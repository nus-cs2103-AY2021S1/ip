package duke.command.meta_commands;

import java.time.LocalDateTime;

public class TimeMetaCommand extends ContentMetaCommand {
    private LocalDateTime time;

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }
}
