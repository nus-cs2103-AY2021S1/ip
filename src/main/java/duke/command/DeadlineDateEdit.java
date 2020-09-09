package duke.command;

import java.util.Date;

import duke.task.Deadline;

public class DeadlineDateEdit implements Edit<Deadline> {
    private Date date;

    public DeadlineDateEdit(Date newDate) {
        date = newDate;
    }

    @Override
    public void apply(Deadline deadline) throws EditingException {
        deadline.setDate(date);
    }
}
