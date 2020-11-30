package luoyi.duke.commands;

import luoyi.duke.common.Message;
import luoyi.duke.common.TextFormatter;
import luoyi.duke.data.IDuke;
import luoyi.duke.data.task.TaskList;
import luoyi.duke.storage.Storage;
import luoyi.duke.ui.Ui;

/**
 * Sort class to encapsulate a sort command.
 * A sort command sorts the task based on some criteria,
 * such as time or description.
 *
 * A command must be initiated with a Duke object before
 * it can execute.
 */
public class SortCommand extends StoringCommand {

    private final SortType sortType;

    /**
     * Returns a new Command.
     *
     * @param duke Duke object to perform action on.
     * @param sortType Type of sort.
     */
    protected SortCommand(IDuke duke, SortType sortType) {
        super(-1, duke);
        this.sortType = sortType;
    }


    /**
     * Returns an SortCommand object.
     *
     * @return SortCommand object with specified properties, not yet initiated with duke.
     */
    public static SortCommand getSortCommand(SortType sortType) {
        return new SortCommand(null, sortType);
    }

    /**
     * Execute the sort.
     * Duke has to be initiated before execution.
     *
     * @return The string prompt after the sort.
     */
    @Override
    public String execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        String output = handleSort();
        System.out.println(TextFormatter.getFormattedText(output));
        return output;
    }

    private String handleSort() {
        Storage storage = duke.getStorage();
        TaskList taskList = duke.getTasks();

        // Sort task based on sort type
        taskList.getList().sort(sortType.getTaskComparator());

        // Save sorted tasks to disk
        storage.save(taskList.getList());

        return "Sort successful!\n" + Ui.displayTasks(taskList);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public SortCommand setDuke(IDuke duke) {
        return new SortCommand(duke, sortType);
    }
}

