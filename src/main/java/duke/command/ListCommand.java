package duke.command;

import duke.tasklist.TaskList;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.task.Task;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.function.Function;

public class ListCommand extends Command {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {
        String start = ui.showList() + "\n";
        AtomicInteger count = new AtomicInteger(1);
        Function<Task, String> taskString = x -> ui.showTask(count.getAndIncrement() ,x);
        BinaryOperator<String> accumulator = (x,y) -> (x + "\n" + y);

        return list.getList().stream()
                             .map(taskString)
                             .reduce(start, accumulator);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
