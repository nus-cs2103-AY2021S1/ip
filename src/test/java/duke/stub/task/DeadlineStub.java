package duke.stub.task;

import duke.task.Task;
import duke.parser.DateTimeParsing;

import java.time.LocalDate;

public class DeadlineStub extends Task {
    public DeadlineStub() {
        super("");
    }

    @Override
    public String toSaveString() {
        return (isDone ? "1" : "0") + "deadline this is a deadline stub /by 2000-01-01 12:00";
    }

    @Override
    public boolean isDueOn(LocalDate date) {
        LocalDate stubDate = DateTimeParsing.parseDate("2000-01-01");
        return date.equals(stubDate);
    }
}
