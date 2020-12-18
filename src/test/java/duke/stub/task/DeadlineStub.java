package duke.stub.task;

import java.time.LocalDate;

import duke.task.Task;
import duke.util.DukeDateTime;

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
        LocalDate stubDate = DukeDateTime.parseDate("2000-01-01");
        return date.equals(stubDate);
    }

    @Override
    public String toString() {
        return "This deadline stub was " + (isDone ? "" : "not") + " marked as done";
    }

    @Override
    public boolean isDueInNDays(int n) {
        return false;
    }
}
