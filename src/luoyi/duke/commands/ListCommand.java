package luoyi.duke.commands;

import luoyi.duke.common.Message;
import luoyi.duke.common.TextFormatter;
import luoyi.duke.data.IDuke;
import luoyi.duke.data.task.TaskList;
import luoyi.duke.ui.Ui;

import java.util.stream.IntStream;

public class ListCommand extends Command {

    private final String date;

    private ListCommand(String date, IDuke duke) {
        super(-1, duke);
        this.date = date;
    }

    public static ListCommand getListCommand(String date) {
        return new ListCommand(date, null);
    }

    @Override
    public IDuke execute() {
        if (duke == null) {
            throw new RuntimeException(Message.ERR_DUKE_NOT_INIT.toString());
        }
        if (date == null) {
            handleDisplay();
        } else {
            handleDisplay(date);
        }
        return duke;
    }

    private void handleDisplay() {
        TaskList list = duke.getTasks();
        if (list.size() == 0) {
            System.out.println(TextFormatter.getFormattedText(
                    Message.ERR_NO_TASK_IN_LIST.toString()));
        } else {
            displayTasks(list);
        }
    }

    private void handleDisplay(String date) {
        TaskList list = duke.getTasks();
        int[] indexes = IntStream
                .range(0, list.size())
                .filter(x -> list.get(x).isSameTime(date))
                .toArray();
        if (indexes.length == 0) {
            System.out.println(TextFormatter.getFormattedText(
                    Message.ERR_NO_MATCHING_TASK.toString()));
        } else {
            Ui.displayTasks(list, indexes, date);
        }
    }

    public void displayTasks(TaskList list) {
        Ui.displayTasks(list);
    }

    @Override
    public Command setDuke(IDuke duke) {
        return new ListCommand(date, duke);
    }
}
