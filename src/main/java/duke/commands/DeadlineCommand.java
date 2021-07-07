package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.TaskManager;

/**
 * <code>DeadlineCommand</code> inherits from the base class <code>Command</code>
 * and will handle the job of adding deadlines to the task manager.
 */
public class DeadlineCommand extends Command {

    private String deadlineName;
    private String deadlineDate;

    private void setDeadlineName(String name) {
        deadlineName = name;
    }

    private void setDeadlineDate(String date) {
        deadlineDate = date;
    }

    /**
     * Adds a <code>Deadline</code> to the task manager.
     * @param input the user input.
     * @return <code>true</code>
     * @throws DukeException if the construction of the <code>Deadline</code> object results in an <code>exception</code>
     */
    @Override
    public boolean execute(String input) throws DukeException {
        switch (stage) {
        case 0:
            setDeadlineName(input);
            setResponse(ui.askDeadlineDate());
            incrementStage();
            break;
        case 1:
            setDeadlineDate(input);
            tm.addDeadline(new Deadline(deadlineName, deadlineDate));
            setResponse("Deadline added");
            setDone();
            break;
        }
        return true;
    }

    /**
     * Sets the utility tools <code>tm</code> and <code>ui</code>.
     * In addition, it sets the initial response to ask for the name
     * of the deadline to be created.
     * @param tm the task manager.
     * @param ui the ui.
     */
    @Override
    public void init(TaskManager tm, Ui ui) {
        setUtility(tm, ui);
        setResponse(ui.askDeadlineName());
    }
}