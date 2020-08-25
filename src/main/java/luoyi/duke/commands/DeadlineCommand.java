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

public class DeadlineCommand extends Command {
    private final String description;
    private final String time;
    private DeadlineCommand(String description, String time, IDuke duke) {
        super(-1, duke);
        this.description = description;
        this.time = time;
    }

    public static DeadlineCommand getDeadlineCommand(String description, String time) {
        return new DeadlineCommand(description, time, null);
    }

    @Override
    public IDuke execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        return handleDeadline(description, time);
    }

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

    public IDuke storeTask(ITask task) {
        Storage storage = duke.getStorage();
        TaskList newList = new TaskList(duke.getTasks().getList());
        newList.add(task);
        storage.save(newList.getList());
        return new Duke(newList, storage);
    }

    @Override
    public Command setDuke(IDuke duke) {
        return new DeadlineCommand(description, time, duke);
    }
}
