package duke.command;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import duke.storage.Storage;
import duke.task.Task;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class FindCommand extends Command {
    private final String target;

    public FindCommand(String detail) {
        this.target = detail.trim();
    }

    @Override
    public String execute(TaskList list, Ui ui, Storage storage) {

        AtomicInteger count = new AtomicInteger(1);
        Predicate<Task> checkDate = x -> x.getName().contains(this.target);
        Function<Task, String> taskString = x -> ui.showTask(count.getAndIncrement(), x);
        BinaryOperator<String> accumulator = (x, y) -> (x + "\n" + y);

        String output = list.getList().stream()
                                      .filter(checkDate)
                                      .map(taskString)
                                      .reduce(accumulator)
                                      .orElse("empty");

        return output.equals("empty") ? ui.showNothingFound() : ui.showFind() + "\n" + output;
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
