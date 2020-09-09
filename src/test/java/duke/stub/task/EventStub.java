package duke.stub.task;

import java.time.LocalDate;

import duke.util.DukeDateTime;
import duke.task.Task;

public class EventStub extends Task {
    public EventStub() {
        super("event this is an event stub");
    }

    @Override
    public String toSaveString() {
        return (isDone ? "1" : "0") + "event this is an event stub /at 2000-01-01 12:00";
    }

    @Override
    public boolean isDueOn(LocalDate date) {
        LocalDate stubDate = DukeDateTime.parseDate("2000-01-01");
        return date.equals(stubDate);
    }
}
