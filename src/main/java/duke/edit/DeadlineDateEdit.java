package duke.edit;

import java.util.Date;

import duke.task.Deadline;

/**
 * An Edit which changes the due date of a Deadline. This Edit will not throw an EditingException.
 */
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
