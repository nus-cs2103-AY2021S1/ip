package luoyi.duke.commands;

import java.util.stream.IntStream;

import luoyi.duke.common.Message;
import luoyi.duke.data.IDuke;
import luoyi.duke.data.task.TaskList;
import luoyi.duke.ui.Ui;

/**
 * ListCommand class to encapsulate a list command.
 * A list command list some tasks in the current duke.
 *
 * A command must be initiated with a Duke object before
 * it can execute.
 */
public class ListCommand extends Command {

    private final String date;

    private ListCommand(String date, IDuke duke) {
        super(-1, duke);
        this.date = date;
    }

    /**
     * Returns a ListCommand object.
     *
     * @param date Date by which the tasks are filtered by.
     * @return ListCommand object with specified properties, not yet initiated with duke.
     */
    public static ListCommand getListCommand(String date) {
        return new ListCommand(date, null);
    }

    /**
     * Executes the list command.
     * Duke object duke must be initiated.
     *
     * @return Resultant duke object.
     */
    @Override
    public String execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        if (date == null) {
            return handleDisplay();
        } else {
            return handleDisplay(date);
        }
    }

    private String handleDisplay() {
        assert duke != null : Message.ERR_DUKE_NOT_INIT.toString();
        TaskList list = duke.getTasks();
        return Ui.displayTasks(list);
    }

    /**
     * Handles the display of list based on a date filter.
     *
     * @param date Date by which the tasks are filtered by.
     */
    private String handleDisplay(String date) {
        assert duke != null : Message.ERR_DUKE_NOT_INIT.toString();
        TaskList list = duke.getTasks();
        int[] indexes = IntStream
                .range(0, list.size())
                .filter(x -> list.get(x).isSameTime(date))
                .toArray();
        return Ui.displayTasks(list, indexes);
    }

    @Override
    public Command setDuke(IDuke duke) {
        return new ListCommand(date, duke);
    }
}
