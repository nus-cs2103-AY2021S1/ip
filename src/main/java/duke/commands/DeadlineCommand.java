package duke.commands;

import duke.DukeException;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.TaskManager;

/**
 * <code>duke.commands.DeadlineCommand</code> inherits from the base class <code>duke.commands.Command</code>
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
     * Using the <code>duke.Ui</code> object in the parent class, it prints out
     * the user interface to ask for the name and due date of the deadline to be created.
     * It uses the <code>Scanner</code> object in the parent class to receive the name and due date
     * of the deadline.
     * It uses the <code>TaskManager</code> object in the parent class and calls
     * its <code>add</code> method with a <code>Deadline</code> object passed as an argument.
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
                tm.add(new Deadline(deadlineName, deadlineDate));
                setResponse("Deadline added"); // TODO: refactor this line
                setDone();
                break;
        }
        return true;
    }

    @Override
    public void init(TaskManager tm, Ui ui) {
        setUtility(tm, ui);
        setResponse(ui.askDeadlineName());
    }
}