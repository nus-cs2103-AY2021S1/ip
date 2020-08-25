package luoyi.duke.commands;

import luoyi.duke.common.Message;
import luoyi.duke.common.TextFormatter;
import luoyi.duke.data.Duke;
import luoyi.duke.data.IDuke;
import luoyi.duke.data.exception.DukeIllegalArgumentException;
import luoyi.duke.data.task.Deadline;
import luoyi.duke.data.task.ITask;
import luoyi.duke.data.task.TaskList;
import luoyi.duke.storage.Storage;

/**
 * DeadlineCommand class to encapsulate a deadline command.
 * A deadline command creates a new deadline task,
 * which has a description and a time.
 *
 * A command must be initiated with a Duke object before
 * it can execute.
 */
public class DeadlineCommand extends Command {
    private final String description;
    private final String time;

    private DeadlineCommand(String description, String time, IDuke duke) {
        super(-1, duke);
        this.description = description;
        this.time = time;
    }

    /**
     * Returns a deadline command object.
     *
     * @param description Description of deadline.
     * @param time Time by which the task is to be completed.
     * @return DeadlineCommand object with specified properties, not yet initiated with duke.
     */
    public static DeadlineCommand getDeadlineCommand(String description, String time) {
        return new DeadlineCommand(description, time, null);
    }

    /**
     * Executes the deadline command.
     * Duke object duke must be initiated.
     *
     * @return Resultant duke object.
     */
    @Override
    public IDuke execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        return handleDeadline(description, time);
    }

    /**
     * Handles the deadline operation.
     * Creates a new deadline task and store it in the returning Duke object.
     *
     * @param description Description of the deadline.
     * @param time Time of deadline.
     * @return The resulting duke object after adding the deadline.
     * @throws DukeIllegalArgumentException If the command parameters are incorrect.
     */
    private IDuke handleDeadline(String description, String time)
            throws DukeIllegalArgumentException {
        if (description.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The description of deadline cannot be empty!");
        }
        if (time.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The time of deadline cannot be empty!");
        }
        ITask task = Deadline.getDeadline(description, time);
        IDuke newDuke = storeTask(task);
        System.out.print(TextFormatter.getFormattedText(
                "Got it. I've added this task:\n\t" + task.toString()
                        + "\nNow you have " +  newDuke.getNumTask()
                        + " task(s) in the list."));
        return newDuke;
    }

    /**
     * Adds task in Duke object.
     * Also invokes storage class to store task list on disk.
     *
     * @param task The tasks to be stored.
     * @return The resultant Duke object with the task stored.
     */
    private IDuke storeTask(ITask task) {
        Storage storage = duke.getStorage();
        TaskList newList = new TaskList(duke.getTasks().getList());
        newList.add(task);
        storage.save(newList.getList());
        return new Duke(newList, storage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Command setDuke(IDuke duke) {
        return new DeadlineCommand(description, time, duke);
    }
}
