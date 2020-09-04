package duke.task;

/*
    @author dianneloh9-reused
    Inspiration from how she created an interface to have tasks that have datetime to implement.
 */

import java.time.LocalDate;
import java.time.LocalDateTime;

public interface TimeBased {
    public DukeDateTime getDukeDateTime();
    public LocalDateTime getDateTime();
    public LocalDate getDate();
}
