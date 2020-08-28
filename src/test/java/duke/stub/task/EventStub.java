package duke.stub.task;

import duke.task.Task;
import duke.parser.DateTimeParsing;

import java.time.LocalDate;

public class EventStub extends Task {
    public EventStub() {
        super("");
    }

    @Override
    public String toSaveString() {
        return (isDone ? "1" : "0") + "event this is an event stub /at 2000-01-01 12:00";
    }

    @Override
    public boolean isDueOn(LocalDate date) {
        LocalDate stubDate = DateTimeParsing.parseDate("2000-01-01");
        return date.equals(stubDate);
    }
}
