package luoyi.duke.commands;

import luoyi.duke.common.Message;
import luoyi.duke.common.TextFormatter;
import luoyi.duke.data.Duke;
import luoyi.duke.data.IDuke;
import luoyi.duke.data.exception.DukeIllegalArgumentException;
import luoyi.duke.data.task.Event;
import luoyi.duke.data.task.ITask;
import luoyi.duke.data.task.TaskList;
import luoyi.duke.storage.Storage;

/**
 * EventCommand class to encapsulate a event command.
 * A event command creates a new event task,
 * which has a description and a time.
 *
 * A command must be initiated with a Duke object before
 * it can execute.
 */
public class EventCommand extends Command {
    private final String description;
    private final String time;
    private EventCommand(String description, String time, IDuke duke) {
        super(-1, duke);
        this.description = description;
        this.time = time;
    }

    /**
     * Returns an EventCommand object.
     *
     * @param description Description of event.
     * @param time Time by which the task is to be completed.
     * @return EventCommand object with specified properties, not yet initiated with duke.
     */
    public static EventCommand getEventCommand(String description, String time) {
        return new EventCommand(description, time, null);
    }


    /**
     * Executes the event command.
     * Duke object duke must be initiated.
     *
     * @return Resultant duke object.
     */
    @Override
    public IDuke execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        return handleEvent(description, time);
    }

    /**
     * Handles the event operation.
     * Creates a new event task and store it in the returning Duke object.
     *
     * @param description Description of the event.
     * @param time Time of event.
     * @return The resulting duke object after adding the event.
     * @throws DukeIllegalArgumentException If the command parameters are incorrect.
     */
    private IDuke handleEvent(String description, String time)
            throws DukeIllegalArgumentException {
        if (description.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The description of event cannot be empty!");
        }
        if (time.matches("\\s*")) {
            throw new DukeIllegalArgumentException(
                    "The time of event cannot be empty!");
        }
        ITask task = Event.getEvent(description, time);
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
    public IDuke storeTask(ITask task) {
        Storage storage = duke.getStorage();
        TaskList newList = new TaskList(duke.getTasks().getList());
        newList.add(task);
        storage.save(newList.getList());
        return new Duke(newList, storage);
    }

    @Override
    public Command setDuke(IDuke duke) {
        return new EventCommand(description, time, duke);
    }
}
